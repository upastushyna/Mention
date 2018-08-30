import React, {Fragment} from 'react'
import Navigation from './Navigation'
import {connect} from 'react-redux'
import {loadSearchPosts} from '../actions/searchPostsActions'
import {loadSearchUsers} from '../actions/searchUsersActions'
import PostsContainer from '../containers/PostsContainer'
import UsersContainer from '../containers/UsersContainer'
import {loadCurrentUser} from '../actions/currentUserActions'
import {deletePost} from '../actions/postsActions'

class SearchPage extends React.Component {
  componentWillMount () {
    if (this.props.foundPosts.length === 0 && this.props.foundUsers.length === 0) {
      console.log(this.props)
      this.props.loadPosts(this.props.match.params.input)
      this.props.loadUsers(this.props.match.params.input)
    }
    if (!this.props.currentUser || !this.props.currentUser.username) {
      this.props.loadCurrentUser()
    }
  }

  follow = followedUser => fetch('/api/follow/add',
    {
      method: 'POST',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        follower: {id: this.props.currentUser.id},
        followedUser: {id: followedUser}})
    }).then(() => this.props.loadCurrentUser());

  unfollow = followedUser => fetch('/api/follow/delete',
    {
      method: 'DELETE',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        follower: {id: this.props.currentUser.id},
        followedUser: {id: followedUser}})
    }).then(() => this.props.loadCurrentUser());

  render () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      return 'Loading...'
    }

    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          {this.props.foundPosts.length === 0 ? 'Sorry, no posts found matching your search'
            : <PostsContainer username={this.props.match.params.input}
              userPosts={this.props.foundPosts}
              loadData={this.props.loadPosts}
              currentUser={this.props.currentUser}/>}
          <div className="users-panel">
            {this.props.foundUsers.length === 0 ? ''
              : <UsersContainer username={this.props.match.params.input}
                loadUsers={this.props.loadUsers}
                users={this.props.foundUsers}
                currentUser={this.props.currentUser}
                follow={this.follow}
                unfollow={this.unfollow}
              />}
          </div>
        </div>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  foundPosts: state.foundPosts,
  foundUsers: state.foundUsers,
  currentUser: state.currentUser,
  deletePost: state.deletePost

})

const mapDispatchToProps = dispatch => ({
  loadPosts: input => dispatch(loadSearchPosts(input)),
  loadUsers: input => dispatch(loadSearchUsers(input)),
  loadCurrentUser: () => dispatch(loadCurrentUser()),
  deletePost: id => dispatch(deletePost(id))
})

export default connect(mapStateToProps, mapDispatchToProps)(SearchPage)