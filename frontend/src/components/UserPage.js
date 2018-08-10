import React, { Fragment } from 'react'
import Navigation from "./Navigation"
import '../css/index.css'
import PostItem from '../containers/PostItem'
import {connect} from 'react-redux'
import {loadPosts} from "../actions/userPageActions";
import HeaderProfile from "../containers/HeaderProfile";

const id = 1;
class UserPage extends React.Component {

  componentWillMount(){
    if(this.props.userPosts.length === 0) {
      this.props.loadData(this.props.match.params.username);
    }
  }

  addPost = () => fetch('/api/post/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({body:this.refs.postInput
          .value, author:{id:id}})
    }).then(() => this.props.loadData(this.props.match.params.username))

  render() {
    const posts = this.props.userPosts.map(post =>
      <PostItem post={post}/>)
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          <HeaderProfile/>
          <input id="postInput" type="text" placeholder="Share your thoughts" ref="postInput"
                 maxLength={255}/><button onClick={() => this.addPost()}>Add new post</button>
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