import React from 'react'
import like from '../img/post-form/like-icon.png'
import likeFilled from '../img/post-form/like-icon-filled.png'
import UserLikesContainer from "./UserLikesContainer";

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

  const showOptions = id => {
    if (document.getElementById('comment' + id).classList.contains('d-none')) {
      document.getElementById('comment' + id).classList.remove('d-none')
    }
  };

  const hide = event => {
    if (!event.target.classList.contains('likers__nav')) {
      let hideButton = document.getElementsByClassName('likers__nav');

      Array.prototype.forEach.call(hideButton, item => {
        if(!item.classList.contains('d-nome')) {
          item.classList.add('d-none')
        }
      })
    }
  };

  window.addEventListener('mousedown', event => hide(event));

  return <div className="d-flex-center">
    {props.likes.find(like => like.user.username === props.currentUser.username)
        ? <img onClick={() => deleteLike()} src={likeFilled} alt="like" className="comment-container__action-img"/>
        : <img onClick={() => addLike()} src={like} alt="dislike" className="comment-container__action-img"/>}
    <span className="post__action-count" onClick={() => showOptions(props.commentId)}>{props.likes.length}</span>
    <div id={"comment" + props.commentId} className="d-none likers__nav">
      <UserLikesContainer likes={props.likes}/>
    </div>
  </div>
}

export default CommentLikeItem