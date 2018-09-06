import React, {Fragment} from 'react'
import Navigation from './Navigation'
import '../css/index.css'
import {loadFeed} from '../actions/feedActions'
import {deletePost} from '../actions/postsActions'
import {deleteComment} from '../actions/commentsActions'
import {connect} from 'react-redux'
import PostsContainer from '../containers/PostsContainer'
import upload from '../img/fileuploadicon.png'
import {webSocketFeed} from "../js/wsConnection";
import loaderReducer from "../reducers/loaderReducer";

class Feed extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      page: 0,
      size: 4,
      pageLoading: false
    };

    this.props.history.listen((location, action) => {
      this.props.loadData(this.props.currentUser.username)
    })
  }

  componentWillMount() {
    if (this.props.feed.length === 0 && this.props.currentUser.username) {
      this.props.loadData(this.props.currentUser.username,
        this.state.page,
        this.state.size,
        this.setState.bind(this, {page: this.state.page + 1}))
    }
  }

  componentDidMount() {
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
      }).then(() => this.props.loadData(
        this.props.currentUser.username, 0, (this.state.page * this.state.size) + 1))
      .then(() => this.refs.postInput.value = '')
      .then(() => this.refs.inputFile.value = null)
      .then(this.refs.addFile.innerText = 'Add file')
  };

  scrollHandler = () => {
    const {page, size, pageLoading} = this.state;
    const {notEmpty, loadData, currentUser, loaderReducer} = this.props;
    if (window.location.pathname === '/') {
      if (loaderReducer) {
        return
      }
      let wrap = document.getElementById("container");
      let content = wrap.offsetHeight;
      let yOffset = window.pageYOffset;
      let y = yOffset + window.innerHeight - 10;
      if (notEmpty && !pageLoading && y >= content) {
        this.setState({pageLoading: true}, () => {
          loadData(currentUser.username,
            page,
            size,
            this.setState.bind(this, {
              page: this.state.page + 1,
              pageLoading: false
            }))
        })
      }
    }
  }

  ;

  changeName = () => {
    if (this.refs.inputFile.files[0]) {
      this.refs.addFile.innerText = this.refs.inputFile.files[0].name
    } else {
      this.refs.addFile.innerText = 'Add file'
    }
  };

  render() {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      return 'Loading...'
    }
    if (!this.props.loaderReducer && this.props.notEmpty) {
      window.onscroll = this.scrollHandler.bind(this)
    }

    return (
      <Fragment key={Feed.id}>
        <Navigation history={this.props.history}/>
        <div id='container' ref="container" className="container">
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
                  <input onChange={() => this.changeName()} className="upload" id="inputFile" ref="inputFile"
                         type="file"/></div>
              </form>
            </div> : ''}
          <div id="aposts_container">
            <PostsContainer
              username={this.props.currentUser.username}
              userPosts={this.props.feed}
              loadData={this.props.loadData}
              currentUser={this.props.currentUser}
              deletePost={this.props.deletePost}
              deleteComment={this.props.deleteComment}
            />
          </div>
        </div>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  feed: state.feed,
  deletePost: state.deletePost,
  deleteComment: state.deleteComment,
  notEmpty: state.notEmpty,
  loaderReducer: state.loaderReducer.loadingPosts
});

const mapDispatchToProps = dispatch => ({
  loadData: (username, page, size, callback) => loadFeed(dispatch, username, page, size, callback),
  deletePost: data => dispatch(deletePost(data)),
  deleteComment: data => dispatch(deleteComment(data))
});

export default connect(mapStateToProps, mapDispatchToProps)(Feed)