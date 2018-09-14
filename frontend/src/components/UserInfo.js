import React, { Fragment } from 'react'
import {Route, Switch, Link} from 'react-router-dom'
import UsersContainer from '../containers/UsersContainer'
import PostsContainer from '../containers/PostsContainer'
import {connect} from 'react-redux'
import {loadFollowed} from '../actions/followedActions'
import {loadFollowing} from '../actions/followingActions'
import {loadLiked} from '../actions/likedActions'
import withRouter from 'react-router-dom/es/withRouter'

class UserInfo extends React.Component {
  componentWillMount () {
    if (this.props.likedPosts.length === 0) {
      this.props.loadData(this.props.username)
    }
  }

  render () {
    return (
      <Fragment key={UserInfo.id}>
        <div className="info-controller">
          <Link className="info-controller__link" to={'/user/' + this.props.username + '/info/followed'}
            onClick={() => this.props.loadFollowedUsers(this.props.username)}>Followed Users</Link>
          <Link className="info-controller__link" to={'/user/' + this.props.username + '/info/following'}
            onClick={() => this.props.loadFollowers(this.props.username)}>Following Users</Link>
          <Link className="info-controller__link" to={'/user/' + this.props.username + '/info/liked'}>Likes</Link>
        </div>

        <Switch>
          <Route exact path='/user/:username/info/followed' render={() =>
            <UsersContainer username={this.props.username}
              loadUsers={this.props.loadFollowedUsers}
              users={this.props.followed}
              currentUser={this.props.currentUser}
              loadCurrentUser={this.props.loadCurrentUser}
              follow={this.props.follow}
              unfollow={this.props.unfollow}/>}/>
          <Route exact path='/user/:username/info/following' render={() =>
            <UsersContainer username={this.props.username}
              loadUsers={this.props.loadFollowers}
              users={this.props.following}
              currentUser={this.props.currentUser}
              loadCurrentUser={this.props.loadCurrentUser}
              follow={this.props.follow}
              unfollow={this.props.unfollow}/>}/>
          <Route path='/user/:username/info/liked' render={() =>
            <PostsContainer username={this.props.username}
              userPosts={this.props.likedPosts}
              loadData={this.props.loadData}
              currentUser={this.props.currentUser}
              deletePost={this.props.deletePost}/>}/>
        </Switch>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  likedPosts: state.likedPosts,
  followed: state.followed,
  following: state.following

});

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadLiked(username)),
  loadFollowedUsers: username => dispatch(loadFollowed(username)),
  loadFollowers: username => dispatch(loadFollowing(username))

});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(UserInfo))