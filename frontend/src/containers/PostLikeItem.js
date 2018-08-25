import React from 'react'
import like from '../img/post-form/like-icon.png'
import likeFilled from '../img/post-form/like-icon-filled.png'

const PostLikeItem = props => {
  const addLike = () => fetch('/api/postlikes/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({user: {id: props.currentUser.id}, post: {id: props.postId}})
    }).then(() => props.loadData(props.username))

  const deleteLike = () => fetch('/api/postlikes/delete',
    {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({user: {id: props.currentUser.id}, post: {id: props.postId}})
    }).then(() => props.loadData(props.username))

  return <div className="post__like-icon d-flex-center">
    {props.likes.find(like => like.user.username === props.currentUser.username)
      ? <img onClick={() => deleteLike()} src={likeFilled} alt="like" className="post__action-img"/>
      : <img onClick={() => addLike()} src={like} alt="dislike" className="post__action-img"/>}
    <span className="post__action-count">{props.likes.length}</span>
  </div>
}

export default PostLikeItem