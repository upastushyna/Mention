import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import {connect} from 'react-redux'
import {loadSearchPosts} from '../actions/searchPostsActions'
import {loadSearchUsers} from '../actions/searchUsersActions'

class SearchContainer extends React.Component {
  constructor (anyparams) {
    super(anyparams)

    this.state = {
      input: ''
    }
  }

  onClick = () => {
    this.props.loadPosts(this.state.input)
    this.props.loadUsers(this.state.input)
  };

  render () {
    return (
      <Fragment>
        <form action="" className="search">
          <input onKeyUp={() => this.setState({input: this.refs.searchInput.value})} id="searchInput"
            ref="searchInput" type="text" className="search__input search__input--non-line"
            placeholder="Search here people or pages..."/>
          <Link to={'/search/' + this.state.input} onClick={() => this.onClick()}>
            <input className="search__btn search__input--non-line" value="Search"/>
          </Link>
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