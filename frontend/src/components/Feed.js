import React, { Fragment } from 'react'
import Navigation from "./Navigation"
import '../css/index.css'
import PostItem from '../containers/PostItem'
import {loadFeed} from "../actions/feedActions";
import {connect} from 'react-redux'
import {USERNAME, ID} from "../constants/hardcode";
import PostsContainer from "../containers/PostsContainer";

const username = "admin";
const id = 1;
class Feed extends React.Component {

  componentWillMount(){
    if(this.props.feed.length === 0) {
      this.props.loadData(USERNAME);
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
    }).then(() => this.refs.postInput.value="");

  render () {
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          <div className="create-post d-flex items-center content-between white-background">
            <textarea className="create-post__input" id="postInput" placeholder="Share your thoughts" ref="postInput"
                   maxLength={255}/>
            <button className="create-post__button" onClick={() => this.addPost()}>Add post</button>
          </div>
          <PostsContainer username={USERNAME}
                          userPosts={this.props.feed}
                          loadData={this.props.loadData}/>
        </div>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  feed: state.feed
});

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadFeed(username))
});

export default connect(mapStateToProps, mapDispatchToProps)(Feed);