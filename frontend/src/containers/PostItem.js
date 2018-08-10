import React, { Fragment } from 'react'
import more from '../img/post-form/more-icon.png'
import like from '../img/post-form/like-icon.png'
import comment from '../img/post-form/comment-icon.png'
import forward from '../img/post-form/forward-icon.png'
import CommentContainer from "./CommentContainer";
import LikeItem from "./LikeItem";
import AddComment from "./AddComment";

const PostItem = props =>
  <Fragment>
    <div className="post">
      <div className="post__header d-flex content-between items-center">
        <div className="profile-small pointer d-flex">
          <img src={props.post.author.profile.avatarUrl} alt="" className="profile-small__avatar"/>
          <div className="profile-small__signature">
            <h2 className="profile-small__username color-dark-grey">{props.post.author.username}</h2>
            <span className="profile-small__alias">{props.post.timestamp.slice(0, 19).replace('T', ' ')}</span>
          </div>
        </div>
        <div className="post__more-icon">
          <img src={more} alt="" className="post__more-img"/>
        </div>
      </div>
      <p className="post__body">
        {props.post.body}
      </p>
      <div className="post__footer d-flex content-between">
        <div className="post__like-icon d-flex items-center">
          <img src={like} alt="" className="post__like-img"/>
          <span className="post__like-number"><LikeItem likes={props.post.likes}/></span>
        </div>
        <div className="post__comment-icon d-flex items-center">
          <img src={comment} alt="" className="post__comment-img"/>
          <span className="post__comment-number">{props.post.comments.length}</span>
          <img src={forward} alt="" className="post__forward-img"/>
          <span className="post__forward-number">5</span>
        </div>
      </div>
    </div>
    <CommentContainer comments={props.post.comments}/>
    {/*<AddComment/>*/}
  </Fragment>

export default PostItem