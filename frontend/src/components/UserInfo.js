import React, { Fragment } from 'react'
import {Route, Switch, Link, NavLink} from 'react-router-dom'
import UsersContainer from "../containers/UsersContainer"
import PostsContainer from "../containers/PostsContainer"
import {connect} from "react-redux";
import {loadFollowed} from "../actions/followedActions";
import {loadFollowing} from "../actions/followingActions";
import {loadLiked} from "../actions/likedActions";
import {USERNAME} from "../constants/hardcode";

class UserInfo extends React.Component {

  componentWillMount(){
    if(this.props.likedPosts.length === 0) {
      this.props.loadData(this.props.username);
    }
  }

  render () {
    return (
      <Fragment>
        <div>
          <NavLink to={"/" + this.props.username + "/info/followed"}>
            <p onClick={() => this.props.loadFollowedUsers(this.props.username)}>Following</p>
          </NavLink>
          <NavLink to={"/" + this.props.username + "/info/following"}>
            <p onClick={() => this.props.loadFollowers(this.props.username)}>Followed</p>
          </NavLink>
          <NavLink to={"/" + this.props.username + "/info/liked"}>
            <p>Likes</p>
          </NavLink>
        </div>

        <Switch>
          <Route exact path='/:username/info/followed' component={() =>
            <UsersContainer username={this.props.username}
                            loadUsers={this.props.loadFollowedUsers}
                            users={this.props.followed} />}/>
          <Route exact path='/:username/info/following' component={() =>
            <UsersContainer username={this.props.username}
                            loadUsers={this.props.loadFollowers}
                            users={this.props.following}/>}/>
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