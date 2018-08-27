import React, { Fragment } from 'react'
import Navigation from './Navigation'
import '../css/index.css'
import {loadFeed} from '../actions/feedActions'
import {connect} from 'react-redux'
import PostsContainer from '../containers/PostsContainer'
import upload from '../img/fileuploadicon.png'

class Feed extends React.Component {
  constructor(props) {
    super(props);

    this.props.history.listen((location, action) => {
      this.props.loadData(this.props.currentUser.username);
    });
  }

  componentWillMount () {
    if (this.props.feed.length === 0 && this.props.currentUser.username)  {
        this.props.loadData(this.props.currentUser.username)
    }
  }

  addPost = event => {
    event.preventDefault();
    const data = new FormData();
    data.append("body", this.refs.postInput.value);
    data.append("id", this.props.currentUser.id);
    if(this.refs.inputFile) {
      const image = this.refs.inputFile.files[0];
      data.append("image", image)
    }

    fetch('/api/posts/add',
      {
        method: 'POST',
        headers: {
          'Authorization': "Bearer " + localStorage.getItem("accessToken")
        },
        body: data
      }).then(() => this.props.loadData(this.props.currentUser.username))
      .then(() => this.refs.postInput.value = '')
      .then(() => this.refs.inputFile.value = null)
  };

  render () {

    if (!this.props.currentUser || !this.props.currentUser.username) {
      this.props.loadCurrentUser()
      return "Loading..."
    }

    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          {!this.props.currentUser.followedUsers.find(follow =>
            follow.followedUser.id === this.props.currentUser.id)?
          <div className="create-post">
<<<<<<< HEAD
            <form encType="multipart/form-data" onSubmit={event => this.addPost(event)}>
              <div className="d-flex-center">
                <textarea className="create-post__input" id="postInput"
                  placeholder="Share your thoughts with world" rows="2" ref="postInput"
=======
            <form encType="multipart/form-data"
                  onSubmit={event => this.addPost(event)}
                  /*onKeyPress={(e) => {(e.key === 'Enter' ? event => this.addPost(event): null)}}*/>
              <div className="d-flex items-center">
                <textarea className="create-post__input" id="postInput"
                          onKeyPress={(e) => {(e.key === 'Enter' ? event => this.addPost(event): null)}}
                  placeholder="Share your thoughts" ref="postInput"
>>>>>>> d8e00c96d10c7e2ac95d94980b782fd7b1d5b12c
                  maxLength={280}/>
                <button type="submit" className="create-post__btn">Add post</button>
                <button type="submit" className="create-post__btn create-post__btn_rounded">+</button>
              </div>
              <div className="upload-file">
              <img src={upload} alt="upload" className="upload-file__icon"/>
              <p>Добавить вложение</p>
              <input className="upload" id="inputFile" ref="inputFile" type="file"/></div>
            </form>
          </div> : ""}
          <PostsContainer username={this.props.currentUser.username}
            userPosts={this.props.feed}
            loadData={this.props.loadData}
            currentUser={this.props.currentUser}/>
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

export default connect(mapStateToProps, mapDispatchToProps)(Feed)