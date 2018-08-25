import React, { Fragment } from 'react'
import more from '../img/post-form/more-icon.png'
import comment from '../img/post-form/comment-icon.png'
import forward from '../img/post-form/forward-icon.png'
import CommentContainer from './CommentContainer'
import PostLikeItem from './PostLikeItem'
import AddComment from './AddComment'
import {getDateFromDb} from '../js/timestamp.js'

const PostItem = props => {
  const rePost = () => fetch('/api/posts/repost',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({author: {id: props.currentUser.id}, parent: {id: props.post.parent ? props.post.parent.id : props.post.id}})
    }).then(() => props.loadData(props.username))

  return <Fragment>
    {props.post.parent
      ? <div className="repost-header">
        <div className="profile-info">
          <img src={props.post.author.profile.avatarUrl} alt="avatar" className="profile-info__avatar"/>
          <div className="profile-info__signature">
            <h2 className="profile-info__username color-white">{props.post.author.username}</h2>
            <span className="profile-info__alias">
              { getDateFromDb(props.post.timestamp)}
            </span>
          </div>
        </div>
      </div>
      : ''}
    <div className="post">
      <div className="post__header">
        <div className="profile-info pointer d-flex items-center">
          <img src={props.post.parent ? props.post.parent.author.profile.avatarUrl
            : props.post.author.profile.avatarUrl} alt="avatar" className="profile-info__avatar"/>
          <div className="profile-info__signature">
            <h2 className="profile-info__username">{props.post.parent
              ? props.post.parent.author.username : props.post.author.username}</h2>
            <span className="profile-info__alias">
              {props.post.parent ? getDateFromDb(props.post.parent.timestamp)
                  : getDateFromDb(props.post.timestamp)}
            </span>
          </div>
        </div>
        <div className="pos-relative">
          <img src={more} alt="actions" className="post__action-img" tabindex="1"/>
          <div className="post__action">Delete post</div>
        </div>
      </div>
      <p className="post__body">
        {props.post.parent ? props.post.parent.body : props.post.body}
      </p>
      {props.post.parent ? props.post.parent.mediaFileUrl
        ? <img className="post__img" alt="like" src={props.post.parent.mediaFileUrl}/> : '' : props.post.mediaFileUrl
        ? <img className="post__img" alt="like-active" src={props.post.mediaFileUrl}/> : ''}
      <div className="post__footer d-flex content-between">
        <PostLikeItem loadData={props.loadData} postId={props.post.id}
          likes={props.post.likes} username={props.username}
          currentUser={props.currentUser}/>
        <div className="post__comment-icon d-flex items-center">
          <img src={comment} alt="comment" className="post__comment-img"/>
          <span className="post__comment-number">{props.post.comments.length}</span>
          <img onClick={() => rePost()} src={forward} alt="repost" className="post__forward-img"/>
          <span className="post__forward-number">{props.post.children.length}</span>
        </div>
      </div>
      <CommentContainer loadData={props.loadData} comments={props.post.comments}
      postId={props.post.id} username={props.username} currentUser={props.currentUser}/>
    <AddComment username={props.username} loadData={props.loadData} postId={props.post.id}
      currentUser={props.currentUser}/>
    </div>

  </Fragment>
}

export default PostItem