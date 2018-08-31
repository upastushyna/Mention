import React from 'react'
import like from '../img/post-form/like-icon.png'
import likeFilled from '../img/post-form/like-icon-filled.png'

const CommentLikeItem = props => {
  const addLike = () => fetch('/api/commentlikes/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': "Bearer " + localStorage.getItem("accessToken")
      },
      body: JSON.stringify({user: {id: props.currentUser.id}, comment: {id: props.commentId}})
    }).then(() => props.loadData(props.username));

  const deleteLike = () => fetch('/api/commentlikes/delete',
    {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': "Bearer " + localStorage.getItem("accessToken")
      },
      body: JSON.stringify({user: {id: props.currentUser.id}, comment: {id: props.commentId}})
    }).then(() => props.loadData(props.username));

  return <div className="d-flex-center">
    {props.likes.find(like => like.user.username === props.currentUser.username)
      ? <img onClick={() => deleteLike()} src={likeFilled} alt="like" className="comment-container__action-img"/>
      : <img onClick={() => addLike()} src={like} alt="dislike" className="comment-container__action-img"/>}
    <span className="post__action-count">{props.likes.length}</span>
  </div>
}

export default CommentLikeItem