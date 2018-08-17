import React, { Fragment } from 'react'
import Navigation from "./Navigation"
import '../css/index.css'
import {loadFeed} from "../actions/feedActions";
import {connect} from 'react-redux'
import PostsContainer from "../containers/PostsContainer";

const username = "admin";
const id = 1;
class Feed extends React.Component {

  componentWillMount(){
    if(this.props.feed.length === 0) {
      this.props.loadData(this.props.currentUser.username);
    }
  }

  addPost = event => {
    event.preventDefault();
    const data = new FormData();
    data.append("body", this.refs.postInput.value);
    data.append("id", this.props.currentUser.id)
    if(this.refs.inputFile) {
      const image = this.refs.inputFile.files[0];
      data.append("image", image)
    }

    fetch('/api/posts/add',
      {
        method: 'POST',
        body: data
      }).then(() => this.props.loadData(this.props.currentUser.username))
      .then(() => this.refs.postInput.value="")
      .then(() => this.refs.inputFile.value=null);
  };

  render () {
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
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
          <PostsContainer username={this.props.currentUser.username}
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