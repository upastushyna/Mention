import React, {Fragment} from 'react'
import {Link, Redirect} from 'react-router-dom'
import {connect} from 'react-redux'
import {loadSearchPosts} from '../actions/searchPostsActions'
import {loadSearchUsers} from '../actions/searchUsersActions'
import search from '../img//search-icon-white.png'

class SearchContainer extends React.Component {
  constructor (anyparams) {
    super(anyparams)

    this.state = {
      input: '',
      redirect: false
    }
  }

  onClick = () => {
    this.props.loadPosts(this.state.input)
    this.props.loadUsers(this.state.input)
  };

  handleSubmit = event => {
    event.preventDefault()
    this.setState({redirect: true})
  };

  redirect = () => {
    if (this.state.redirect) {
      return <Redirect to={'/search/' + this.state.input}/>
    }
  }

  render () {
    return (
      <Fragment>
        {this.redirect()}
        <form onSubmit={event => this.handleSubmit(event) } className="d-flex-center">
          <input pattern=".{1,}" required title="1 characters minimum" onKeyUp={() =>
            this.setState({input: this.refs.searchInput.value})} id="searchInput"
          ref="searchInput" type="text" className="search__input"
          placeholder="Search..."/>
          <input type="image" className="search__btn" src={search} alt="search"/>
        </form>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  foundPosts: state.foundPosts,
  foundUsers: state.foundUsers
})

const mapDispatchToProps = dispatch => ({
  loadPosts: input => dispatch(loadSearchPosts(input)),
  loadUsers: input => dispatch(loadSearchUsers(input))
})

export default connect(mapStateToProps, mapDispatchToProps)(SearchContainer)