import React, { Fragment } from 'react'
import Navigation from "./Navigation"
import {Route, Switch, Link} from 'react-router-dom'
import '../css/index.css'
import {connect} from 'react-redux'
import {loadPosts} from "../actions/userPageActions";
import HeaderProfile from "../containers/HeaderProfile";
import {ID, USERNAME} from "../constants/hardcode"
import PostsContainer from "../containers/PostsContainer"
import UserInfo from "./UserInfo"


const id = 1;
class UserPage extends React.Component {

  componentWillMount(){
    if(this.props.userPosts.length === 0) {
      this.props.loadData(this.props.match.params.username);
    }
  }

  addPost = () => fetch('/api/posts/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({body:this.refs.postInput
          .value, author:{id:ID}})
    }).then(() => this.props.loadData(this.props.match.params.username))
    .then(() => this.refs.postInput.value="");

  render() {
    /*const posts = this.props.userPosts.map(post =>
      <PostItem username={this.props.match.params.username}
                loadData={this.props.loadData} post={post}/>);*/
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          <HeaderProfile/>
          <Link to={"/" + this.props.match.params.username + "/info"}>
            <p>Info</p>
          </Link>
          <Link to={"/" + this.props.match.params.username}>
            <p>Posts</p>
          </Link>
          <div className="create-post d-flex items-center content-between white-background">
            <textarea className="create-post__input" id="postInput" placeholder="Share your thoughts" ref="postInput"
                      maxLength={255}/>
            <button className="create-post__button" onClick={() => this.addPost()}>Add post</button>
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

});

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadPosts(username))
});

export default connect(mapStateToProps, mapDispatchToProps)(UserPage);