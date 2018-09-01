import React, {Fragment} from 'react'
import Navigation from './Navigation'
import {Route, Switch, Link} from 'react-router-dom'
import '../css/index.css'
import {connect} from 'react-redux'
import {loadPosts} from '../actions/userPageActions'
import {loadUser} from '../actions/userPicturesActions'
import HeaderProfile from '../containers/HeaderProfile'
import PostsContainer from '../containers/PostsContainer'
import UserInfo from './UserInfo'
import info from '../img/info-icon.png'
import posts from '../img/posts-icon.png'
import {loadCurrentUser} from '../actions/currentUserActions'
import FollowButton from '../containers/FollowButton'
import UnffollowButton from '../containers/UnffollowButton'
import upload from '../img/fileuploadicon.png'
import {deletePost} from '../actions/postsActions'

class UserPage extends React.Component {
  constructor (props) {
    super(props)

    this.props.history.listen((location, action) => {
      console.log(this.props.history)
      this.props.loadUser(this.props.match.params.username)
      this.props.loadData(this.props.match.params.username)
    })
  }

  componentWillMount () {
    if (this.props.userPosts.length === 0) {
      this.props.loadData(this.props.match.params.username)
    }
    if (!this.props.user || !this.props.user.username) {
      this.props.loadUser(this.props.match.params.username)
    }
    if (!this.props.currentUser || !this.props.currentUser.username) {
      this.props.loadCurrentUser()
    }
  }

  follow = followedUser => fetch('/api/follow/add',
    {
      method: 'POST',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        follower: {id: this.props.currentUser.id},
        followedUser: {id: followedUser}
      })
    }).then(() => this.props.loadCurrentUser());

  unfollow = followedUser => fetch('/api/follow/delete',
    {
      method: 'DELETE',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        follower: {id: this.props.currentUser.id},
        followedUser: {id: followedUser}
      })
    }).then(() => this.props.loadCurrentUser());

  addPost = event => {
    event.preventDefault()
    const data = new FormData()
    data.append('body', this.refs.postInput.value)
    data.append('id', this.props.currentUser.id)
    if (this.refs.inputFile) {
      const image = this.refs.inputFile.files[0]
      data.append('image', image)
    }

    fetch('/api/posts/add',
      {
        method: 'POST',
        headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
        },
        body: data
      }).then(() => this.props.loadData(this.props.match.params.username))
      .then(() => this.refs.postInput.value = '')
      .then(() => this.refs.inputFile.value = null)
      .then(this.refs.addFile.innerText = 'Add file')
  };

  changeName = () => {
    if (this.refs.inputFile.files[0]) {
      this.refs.addFile.innerText = this.refs.inputFile.files[0].name;
    } else {
      this.refs.addFile.innerText = "Add file";
    }
  };

  render () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      return 'Loading...'
    }

    return (
        <Fragment key={UserPage.id}>
          <Navigation/>
          <div className="user-navigation">
            <HeaderProfile user={this.props.user}/>
            <div className="user-navigation__links">
              <Link className="user-nav-links__item" to={'/' + this.props.match.params.username + '/info'}>
                <img src={info} alt="info" className="user-navigation__icon"/>
                <h4 className="user-nav-links__text">info</h4>
              </Link>
              <Link className="user-nav-links__item" to={'/' + this.props.match.params.username}>
                <img src={posts} alt="feed" className="user-navigation__icon"/>
                <h4 className="user-nav-links__text">profile</h4>
              </Link>
            </div>
            <div className="following shadow-button">
              {this.props.currentUser.followedUsers.find(follow =>
                  follow.followedUser.id === this.props.user.id) ?
                  <UnffollowButton unfollow={this.unfollow} followedUser={this.props.user.id}/> :
                  <FollowButton follow={this.follow} followedUser={this.props.user.id}/>
              }
            </div>
          </div>
          <div className="following shadow-button">
            {this.props.currentUser.followedUsers.find(follow =>
              follow.followedUser.id === this.props.user.id)
              ? <UnffollowButton unfollow={this.unfollow} followedUser={this.props.user.id}/>
              : <FollowButton follow={this.follow} followedUser={this.props.user.id}/>
            }
          </div>
        </div>

        <div className="create-post">
          <form encType="multipart/form-data" onSubmit={event => this.addPost(event)}>
            <div className="d-flex-center content-between">
              <textarea className="create-post__input" id="postInput"
                placeholder="Share your thoughts" ref="postInput"
                maxLength={280}/>
              <button type="submit" className="create-post__btn btn-action">Add post</button>
              <button type="submit" className="btn-action btn-action_rounded create-post__btn_rounded">+</button>
            </div>
            <div className="upload-file">
              <img src={upload} alt="upload" className="upload-file__icon"/>
              <p ref="addFile">Add file</p>
              <input onChange={() => this.changeName()} className="upload" id="inputFile" ref="inputFile" type="file"/></div>
          </form>
        </div>
        <Switch>
          <Route exact path={this.props.match.path} component={() =>
            <PostsContainer username={this.props.match.params.username}
              userPosts={this.props.userPosts}
              loadData={this.props.loadData}
              currentUser={this.props.currentUser}
              deletePost={this.props.deletePost}/>}/>
          <Route path='/:username/info' component={() =>
            <UserInfo username={this.props.match.params.username}
              currentUser={this.props.currentUser}
              loadCurrentUser={this.props.loadCurrentUser}
              follow={this.follow} unfollow={this.unfollow}/>}/>
        </Switch>

      </Fragment>

    )
  }
}

const mapStateToProps = state => ({
  userPosts: state.userPosts,
  user: state.user,
  currentUser: state.currentUser,
  deletePost: state.deletePost

})

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadPosts(username)),
  loadUser: username => dispatch(loadUser(username)),
  loadCurrentUser: () => dispatch(loadCurrentUser()),
  deletePost: id => dispatch(deletePost(id))
})
export default connect(mapStateToProps, mapDispatchToProps)(UserPage)