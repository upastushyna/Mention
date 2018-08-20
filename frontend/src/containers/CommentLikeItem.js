import React from 'react'
import like from '../img/post-form/like-icon.png'
import likeFilled from '../img/post-form/like-icon-filled.png'
import {ID, USERNAME} from '../constants/hardcode'

const CommentLikeItem = props => {
  const addLike = () => fetch('/api/commentlikes/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({user: {id: props.currentUser.id}, comment: {id: props.commentId}})
    }).then(() => props.loadData(props.username))

  const deleteLike = () => fetch('/api/commentlikes/delete',
    {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({user: {id: props.currentUser.id}, comment: {id: props.commentId}})
    }).then(() => props.loadData(props.username))

  return <div className="comment-container__like-icon d-flex items-center">
    {props.likes.find(like => like.user.username === props.currentUser.username)
      ? <img onClick={() => deleteLike()} src={likeFilled} alt="" className="comment-container__like-img"/>
      : <img onClick={() => addLike()} src={like} alt="" className="comment-container__like-img"/>}
    <span className="comment-container__like-number">{props.likes.length}</span>
  </div>
}

export default CommentLikeItem