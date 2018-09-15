import React, { Fragment } from 'react'
import Navigation from './Navigation'
import '../css/index.css'
import {loadFeed} from '../actions/feedActions'
import {deletePost} from '../actions/postsActions'
import {deleteComment} from '../actions/commentsActions'
import {connect} from 'react-redux'
import PostsContainer from '../containers/PostsContainer'
import upload from '../img/fileuploadicon.png'
import Preloader from "../containers/Preloader";
import withRouter from "react-router-dom/es/withRouter";
import {webSocketFeed} from "../js/wsConnection";
import {loadCurrentUser} from "../actions/currentUserActions";

class Feed extends React.Component {
  constructor (props) {
    super(props)
    this.props.history.listen((location, action) => {
      this.props.loadData(this.props.currentUser.username)
    })
  }

  componentWillMount () {
    if(!this.props.currentUser.username) {
      this.props.loadCurrentUser()
    }
    if (this.props.feed.length === 0 && this.props.currentUser.username) {
      this.props.loadData(this.props.currentUser.username)
    }
  }

  componentWillReceiveProps () {
    if (this.props.feed.length === 0) {
      this.props.loadData(this.props.currentUser.username)
    }
  }

  componentDidMount () {
    webSocketFeed(this.props.loadData);
  }

  addPost = event => {
    event.preventDefault();
    const data = new FormData();
    data.append('body', this.refs.postInput.value);
    data.append('id', this.props.currentUser.id);
    if (this.refs.inputFile) {
      const image = this.refs.inputFile.files[0];
      data.append('image', image)
    }

    fetch('/api/posts/add',
      {
        method: 'POST',
        headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
        },
        body: data
      }).then(() => this.props.loadData(this.props.currentUser.username))
      .then(() => this.refs.postInput.value = '')
      .then(() => this.refs.inputFile.value = null)
      .then(this.refs.addFile.innerText = 'Add file')
  };

  changeName = () => {
    if (this.refs.inputFile.files[0]) {
      this.refs.addFile.innerText = this.refs.inputFile.files[0].name
    } else {
      this.refs.addFile.innerText = 'Add file'
    }
  };

  render () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      this.props.loadCurrentUser();
      return <Preloader/>;
    }

    const myRef = React.createRef();

    console.log(myRef)

    return (
      <Fragment key={Feed.id}>
        <div ref="container">
          {this.props.currentUser.followedUsers.find(follow =>
            follow.followedUser.id === this.props.currentUser.id)
            ? <div className="create-post">
              <form encType="multipart/form-data" onSubmit={event => this.addPost(event)}>
                <div className="d-flex-center">
                  <textarea className="create-post__input" id="postInput"
                    placeholder="Share your thoughts with world" rows="2" ref="postInput"
                    maxLength={280}/>
                  <button type="submit" className="create-post__btn btn-action">Add post</button>
                  <button type="submit" className="create-post__btn_rounded btn-action btn-action_rounded">+</button>
                </div>
                <div className="upload-file">
                  <img src={upload} alt="upload" className="upload-file__icon"/>
                  <p ref="addFile">Add file</p>
                  <input onChange={() => this.changeName()} className="upload" id="inputFile" ref="inputFile" type="file"/></div>
              </form>
            </div> : ''}
          <PostsContainer
              username={this.props.currentUser.username}
              userPosts={this.props.feed}
              loadData={this.props.loadData}
              currentUser={this.props.currentUser}
              deletePost={this.props.deletePost}
              deleteComment={this.props.deleteComment}
          />

        </div>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  feed: state.feed,
  deletePost: state.deletePost,
  deleteComment: state.deleteComment,
  currentUser: state.currentUser
});

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadFeed(username)),
  deletePost: data => dispatch(deletePost(data)),
  deleteComment: data => dispatch(deleteComment(data)),
  loadCurrentUser: () => dispatch(loadCurrentUser())
});

export default connect(mapStateToProps, mapDispatchToProps)(Feed)
