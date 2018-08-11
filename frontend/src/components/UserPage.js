import React, { Fragment } from 'react'
import Navigation from "./Navigation"
import '../css/index.css'
import PostItem from '../containers/PostItem'
import {connect} from 'react-redux'
import {loadPosts} from "../actions/userPageActions";
import HeaderProfile from "../containers/HeaderProfile";
import {ID} from "../constants/hardcode"


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
    const posts = this.props.userPosts.map(post =>
      <PostItem username={this.props.match.params.username}
                loadData={this.props.loadData} post={post}/>);
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          <HeaderProfile/>
          <div className="create-post d-flex items-center content-between">
            <textarea className="create-post__input" id="postInput" placeholder="Share your thoughts" ref="postInput"
                      maxLength={255}/>
            <button className="create-post__button" onClick={() => this.addPost()}>Add post</button>
          </div>
          {posts}
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