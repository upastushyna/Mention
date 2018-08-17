import React, { Fragment } from 'react'
import {Route, Switch, Link} from 'react-router-dom'
import UsersContainer from "../containers/UsersContainer"
import PostsContainer from "../containers/PostsContainer"
import {connect} from "react-redux";
import {loadFollowed} from "../actions/followedActions";
import {loadFollowing} from "../actions/followingActions";
import {loadLiked} from "../actions/likedActions";

class UserInfo extends React.Component {

  componentWillMount(){
    if(this.props.likedPosts.length === 0) {
      this.props.loadData(this.props.username);
    }
  }

  render () {
    return (
      <Fragment>
        <div className="info-controller">
          <Link className="info-controller__link color-white" to={"/" + this.props.username + "/info/followed"}
                onClick={() => this.props.loadFollowedUsers(this.props.username)}>Followed Users</Link>
          <Link className="info-controller__link color-white" to={"/" + this.props.username + "/info/following"}
                onClick={() => this.props.loadFollowers(this.props.username)}>Following Users</Link>
          <Link className="info-controller__link color-white" to={"/" + this.props.username + "/info/liked"}>Likes</Link>
        </div>

        <Switch>
          <Route exact path='/:username/info/followed' component={() =>
            <UsersContainer username={this.props.username}
                            loadUsers={this.props.loadFollowedUsers}
                            users={this.props.followed}
                            currentUser={this.props.currentUser}
                            loadCurrentUser={this.props.loadCurrentUser}/>}/>
          <Route exact path='/:username/info/following' component={() =>
            <UsersContainer username={this.props.username}
                            loadUsers={this.props.loadFollowers}
                            users={this.props.following}
                            currentUser={this.props.currentUser}
                            loadCurrentUser={this.props.loadCurrentUser}/>}/>
          <Route exact path='/:username/info/liked' component={() =>
            <PostsContainer username={this.props.username}
                            userPosts={this.props.likedPosts}
                            loadData={this.props.loadData}/>}/>
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
  loadFollowers: username => dispatch(loadFollowing(username)),

});

export default connect(mapStateToProps, mapDispatchToProps)(UserInfo);