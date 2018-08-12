import React from 'react'
import like from '../img/post-form/like-icon.png'
import likeFilled from '../img/post-form/like-icon-filled.png'
import {ID, USERNAME} from "../constants/hardcode";

const PostLikeItem = props => {

  const addLike = () => fetch('/api/postlikes/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({user:{id:ID}, post:{id:props.postId}})
    }).then(() => props.loadData(props.username));

  const deleteLike = () => fetch('/api/postlikes/delete',
    {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({user:{id:ID}, post:{id:props.postId}})
    }).then(() => props.loadData(props.username));

  return <div className="post__like-icon d-flex items-center">
    {props.likes.find(like => like.user.username === USERNAME)?
    <img onClick={() => deleteLike()} src={likeFilled} alt="" className="post__like-img"/>:
    <img onClick={() => addLike()} src={like} alt="" className="post__like-img"/>}
    <span className="post__like-number">
      <span>{props.likes.length}</span>
    </span>
  </div>
}

export default PostLikeItem