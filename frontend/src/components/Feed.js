import React, { Fragment } from 'react'
import '../css/index.css'
import {loadFeed} from '../actions/feedActions'
import {deletePost} from '../actions/postsActions'
import {deleteComment} from '../actions/commentsActions'
import {connect} from 'react-redux'
import PostsContainer from '../containers/PostsContainer'
import upload from '../img/fileuploadicon.png'
import Preloader from "../containers/Preloader";
import {webSocketFeed} from "../js/wsConnection";
import {loadCurrentUser} from "../actions/currentUserActions";

class Feed extends React.Component {
  constructor (props) {
    super(props);
    this.state = {
      feedLoaded: false
    };

    this.props.history.listen((location, action) => {
      this.props.loadData({username:this.props.currentUser.username, changeState:this.changeState})
    })
  }

  componentWillMount () {
    if(!this.props.currentUser.username) {
      this.props.loadCurrentUser()
    }
    if (!this.state.feedLoaded && this.props.currentUser.username) {
      this.props.loadData({username:this.props.currentUser.username, changeState:this.changeState})
    }
  }

  componentWillReceiveProps () {
    if (!this.state.feedLoaded) {
      this.props.loadData({username:this.props.currentUser.username, changeState:this.changeState})
    }
  }

  componentDidMount () {
    webSocketFeed(this.props.loadData);
  }

  changeState = () => {
    this.setState({feedLoaded:true})
  };

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

  searchTimeOut = () => {
    let empty = document.querySelector('.empty-state');
    let loader = document.querySelector('.loader');

    if (empty !== null && loader !== null) {
      empty.style.display = "none";

      setTimeout(() => {
        loader.style.display = "none";
        empty.style.display = "flex";
      }, 2000)
    }
  };

  render () {
    const {feed, loadData, currentUser, deletePost, deleteComment} = this.props;

    if (!this.state.feedLoaded) {
      return <Preloader/>;
    }

    if (this.props.feed.length === 0) {
      this.searchTimeOut();
    }

    return (
      <Fragment key={Feed.id}>
        <div ref="container">
          {currentUser.followedUsers.find(follow =>
            follow.followedUser.id === currentUser.id)
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
              username={currentUser.username}
              userPosts={feed}
              loadData={loadData}
              currentUser={currentUser}
              deletePost={deletePost}
              deleteComment={deleteComment}
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
