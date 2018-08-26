import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import {connect} from 'react-redux'
import {loadSearchPosts} from '../actions/searchPostsActions'
import {loadSearchUsers} from '../actions/searchUsersActions'
import search from '../img//search-icon-white.png'

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
        <form action="#" className="d-flex-center">
          <input onKeyUp={() => this.setState({input: this.refs.searchInput.value})} id="searchInput"
            ref="searchInput" type="text" className="search__input"
            placeholder="Search..."/>
          <Link to={'/search/' + this.state.input} className="search__btn" onClick={() => this.onClick()}>
          <img src={search} alt="search"/>
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