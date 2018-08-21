import React, { Fragment } from 'react'
import more from '../img/post-form/more-icon.png'
import comment from '../img/post-form/comment-icon.png'
import forward from '../img/post-form/forward-icon.png'
import CommentContainer from './CommentContainer'
import PostLikeItem from './PostLikeItem'
import AddComment from './AddComment'

const PostItem = props => {
  const rePost = () => fetch('/api/posts/repost',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({author: {id: props.currentUser.id}, parent: {id: props.post.parent ? props.post.parent.id : props.post.id}})
    }).then(() => props.loadData(props.username));

  return <Fragment>
    {props.post.parent
      ? <div className="repost-header">
        <div className="profile-small d-flex">
          <img src={props.post.author.profile.avatarUrl} alt="" className="profile-small__avatar"/>
          <div className="profile-small__signature">
            <h2 className="profile-small__username color-white">{props.post.author.username}</h2>
            <span className="profile-small__alias color-white">
              {props.post.timestamp.slice(0, 19).replace('T', ' ')}
            </span>
          </div>
        </div>
      </div>
      : ''}
    <div className="post white-background">
      <div className="post__header d-flex content-between items-center">
        <div className="profile-small pointer d-flex">
          <img src={props.post.parent ? props.post.parent.author.profile.avatarUrl
            : props.post.author.profile.avatarUrl} alt="" className="profile-small__avatar"/>
          <div className="profile-small__signature">
            <h2 className="profile-small__username color-dark-grey">{props.post.parent
              ? props.post.parent.author.username : props.post.author.username}</h2>
            <span className="profile-small__alias">
              {props.post.parent ? props.post.parent.timestamp.slice(0, 19).replace('T', ' ')
                : props.post.timestamp.slice(0, 19).replace('T', ' ')}
            </span>
          </div>
        </div>
        <div className="post__more-icon">
          <img src={more} alt="" className="post__more-img main-item" tabindex="1"/>
          <ul className="sub-menu">
            <li className="sub-menu__item"><span>Delete post</span></li>
            <li className="sub-menu__item"><span>Edit post</span></li>
          </ul>
        </div>
      </div>
      <p className="post__body">
        {props.post.parent ? props.post.parent.body : props.post.body}
      </p>
      {props.post.parent ? props.post.parent.mediaFileUrl
        ? <img className="post__img" src={props.post.parent.mediaFileUrl}/> : '' : props.post.mediaFileUrl
        ? <img className="post__img" src={props.post.mediaFileUrl}/> : ''}
      <div className="post__footer d-flex content-between">
        <PostLikeItem loadData={props.loadData} postId={props.post.id}
          likes={props.post.likes} username={props.username}
          currentUser={props.currentUser}/>
        <div className="post__comment-icon d-flex items-center">
          <img src={comment} alt="" className="post__comment-img"/>
          <span className="post__comment-number">{props.post.comments.length}</span>
          <img onClick={() => rePost()} src={forward} alt="" className="post__forward-img"/>
          <span className="post__forward-number">{props.post.children.length}</span>
        </div>
      </div>
    </div>
    <CommentContainer loadData={props.loadData} comments={props.post.comments}
      postId={props.post.id} username={props.username} currentUser={props.currentUser}/>
    <AddComment username={props.username} loadData={props.loadData} postId={props.post.id}
      currentUser={props.currentUser}/>
  </Fragment>
}

export default PostItem