import React, { Fragment } from 'react'
import more from '../img/post-form/more-icon.png'
import CommentLikeItem from './CommentLikeItem'
import {getDateFromDb} from '../js/timestamp.js'
import {Link} from 'react-router-dom'

const showDelete = id => {
  if (document.getElementById('commentDelete' + id).classList.contains('d-none')) {
    document.getElementById('commentDelete' + id).classList.remove('d-none')
  }
};

const hideOptions = event => {
  if (!event.target.classList.contains('post__action')) {
    let hideButton = document.getElementsByClassName('post__action');

    Array.prototype.forEach.call(hideButton, item => {
      if(!item.classList.contains('d-nome')) {
        item.classList.add('d-none')
      }
    })
  }
};

const CommentContainer = props => {
  window.addEventListener('mousedown', event => hideOptions(event));

  return props.comments.map(comment =>
    <Fragment key={comment.id}>
      <div className="comment-container">
        <div className="d-flex-center content-between">
          <Link to={'/' + comment.commentator.username} className="post__link">
            <div className="profile-info">
              <img src={comment.commentator.profile.avatarUrl} alt="" className="profile-info__avatar"/>
              <div className="profile-info__signature">
                <h2 className="profile-info__username">{comment.commentator.username}</h2>
                <span className="profile-info__alias">{getDateFromDb(comment.timestamp)}</span>
              </div>
            </div>
          </Link>
          {comment.commentator.id === props.currentUser.id?
            <div className="pos-relative">
              <img id={"commentOptions" + comment.id} src={more} alt="more"
                   onClick={() => showDelete(comment.id)}
                   className="comment-container__action-img" tabIndex="1"/>
              <div id={"commentDelete" + comment.id} className="post__action d-none"
                   onClick={() => props.deleteComment({id:comment.id,
                     loadPosts:props.loadData,
                     username:props.username})}>Delete comment
              </div>
          </div> : ""}
        </div>
        <p className="comment-container__body">
          {comment.body}
        </p>
        <div className="comment-container__footer d-flex-center content-between">
          <CommentLikeItem likes={comment.commentLikes} username={props.username}
                           commentId={comment.id} loadData={props.loadData}
                           currentUser={props.currentUser}/>
        </div>
      </div>
    </Fragment>);
}

export default CommentContainer