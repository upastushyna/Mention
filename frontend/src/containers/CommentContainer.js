import React, { Fragment } from 'react'
import more from '../img/post-form/more-icon.png'
import like from '../img/post-form/like-icon.png'
import CommentLikeItem from "./CommentLikeItem";

const CommentContainer = props =>
  props.comments.map(comment =>
  <Fragment>
    <div className="comment-container">
      <div className="comment-container__header d-flex items-center content-between">
        <div className="profile-small pointer d-flex items-center">
          <img src={comment.commentator.profile.avatarUrl} alt="" className="profile-small__avatar"/>
          <div className="profile-small__signature">
            <h2 className="profile-small__username color-dark-grey">{comment.commentator.username}</h2>
            <span className="profile-small__alias">{comment.timestamp}</span>
          </div>
        </div>
        <div className="comment-container__more-icon">
          <img src={more} alt="" className="comment-container__more-img"/>
        </div>
      </div>
      <p className="comment-container__body">
        {comment.body}
      </p>
      <div className="comment-container__footer d-flex content-between">
        <CommentLikeItem likes={comment.commentLikes} username={props.username}
                         commentId={comment.id} loadData={props.loadData}/>
      </div>
    </div>
  </Fragment>)

export default CommentContainer