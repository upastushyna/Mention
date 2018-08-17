import React, { Fragment } from 'react'
import Navigation from "./Navigation"
import {Route, Switch, Link} from 'react-router-dom'
import '../css/index.css'
import {connect} from 'react-redux'
import {loadPosts} from "../actions/userPageActions";
import {loadUser} from "../actions/userPicturesActions";
import HeaderProfile from "../containers/HeaderProfile";
import {ID} from "../constants/hardcode"
import PostsContainer from "../containers/PostsContainer"
import UserInfo from "./UserInfo"
import info from '../img/info-icon.png'
import posts from '../img/posts-icon.png'

class UserPage extends React.Component {

  componentWillMount(){
    if(this.props.userPosts.length === 0) {
      this.props.loadData(this.props.match.params.username);
    }
    if(!this.props.user || !this.props.user.username) {
      this.props.loadUser(this.props.match.params.username);
    }
  }

  addPost = event => {
    event.preventDefault();
    const data = new FormData();
    data.append("body", this.refs.postInput.value);
    data.append("id", ID)
    if(this.refs.inputFile) {
      const image = this.refs.inputFile.files[0];
      data.append("image", image)
    }

    fetch('/api/posts/add',
      {
        method: 'POST',
        body: data
      }).then(() => this.props.loadData(this.props.match.params.username))
      .then(() => this.refs.postInput.value="")
      .then(() => this.refs.inputFile.value=null);
  };

  render() {
    /*const posts = this.props.userPosts.map(post =>
      <PostItem username={this.props.match.params.username}
                loadData={this.props.loadData} post={post}/>);*/
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          <div className="user-navigation">
            <HeaderProfile user={this.props.user}/>
            <Link className="user-navigation__info" to={"/" + this.props.match.params.username + "/info"}>
              <img src={info} alt="" className="user-navigation__icon"/>
              <h4 className="user-navigation__hover">info</h4>
            </Link>
            <Link className="user-navigation__posts" to={"/" + this.props.match.params.username}>
              <img src={posts} alt="" className="user-navigation__icon"/>
              <h4 className="user-navigation__hover">profile</h4>
            </Link>
          </div>
          <div className="create-post white-background">
            <form encType="multipart/form-data" onSubmit={event => this.addPost(event)}>
              <div className="d-flex items-center content-between">
                <textarea className="create-post__input" id="postInput"
                          placeholder="Share your thoughts" ref="postInput"
                          maxLength={280}/>
                <button type="submit" className="create-post__button">Add post</button>
              </div>
              <input className="upload" id="inputFile" ref="inputFile" type="file"/>
            </form>
          </div>
          {/*{posts}*/}
          <Switch>
            <Route exact path={this.props.match.path} component={() =>
              <PostsContainer username={this.props.match.params.username}
                              userPosts={this.props.userPosts}
                              loadData={this.props.loadData}/>}/>
            <Route path='/:username/info' component={() =>
              <UserInfo username={this.props.match.params.username}/>}/>
          </Switch>
        </div>
      </Fragment>

    );
  }
}

const mapStateToProps = state => ({
  userPosts: state.userPosts,
  user: state.user

});

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadPosts(username)),
  loadUser: username => dispatch(loadUser(username))
});

export default connect(mapStateToProps, mapDispatchToProps)(UserPage);