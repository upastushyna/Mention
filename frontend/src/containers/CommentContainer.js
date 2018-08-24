import React, { Fragment } from 'react'
import more from '../img/post-form/more-icon.png'
import CommentLikeItem from './CommentLikeItem'

const CommentContainer = props =>
  props.comments.map(comment =>
    <Fragment>
      <div className="comment-container">
        <div className="comment-container__header d-flex items-center content-between">
          <div className="profile-info pointer">
            <img src={comment.commentator.profile.avatarUrl} alt="" className="profile-info__avatar"/>
            <div className="profile-info__signature">
              <h2 className="profile-info__username">{comment.commentator.username}</h2>
              <span className="profile-info__alias">{comment.timestamp}</span>
            </div>
          </div>

          <div className="pos-relative">
            <img src={more} alt="more" className="comment-container__action-img" tabindex="1"/>
            <div className="post__action">Delete comment</div>
          </div>
        </div>
        <p className="comment-container__body">
          {comment.body}
        </p>
        <div className="comment-container__footer d-flex content-between">
          <CommentLikeItem likes={comment.commentLikes} username={props.username}
            commentId={comment.id} loadData={props.loadData}
            currentUser={props.currentUser}/>
        </div>
      </div>
    </Fragment>)

export default CommentContainer