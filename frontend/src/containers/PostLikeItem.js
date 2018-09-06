import React from 'react'
import like from '../img/post-form/like-icon.png'
import likeFilled from '../img/post-form/like-icon-filled.png'
import UsersContainer from "./UsersContainer";
import UserLikesContainer from "./UserLikesContainer";

const PostLikeItem = props => {
  const addLike = () => fetch('/api/postlikes/add',
      {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': "Bearer " + localStorage.getItem("accessToken")
        },
        body: JSON.stringify({user: {id: props.currentUser.id}, post: {id: props.postId}})
      }).then(() => props.loadData(props.username))

  const deleteLike = () => fetch('/api/postlikes/delete',
      {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': "Bearer " + localStorage.getItem("accessToken")
        },
        body: JSON.stringify({user: {id: props.currentUser.id}, post: {id: props.postId}})
      }).then(() => props.loadData(props.username))

  const showOptions = id => {
    if (document.getElementById('post' + id).classList.contains('d-none')) {
      document.getElementById('post' + id).classList.remove('d-none')
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
        ? <img onClick={() => deleteLike()} src={likeFilled} alt="like" className="post__action-img"/>
        : <img onClick={() => addLike()} src={like} alt="dislike" className="post__action-img"/>}
    <span className="post__action-count" onClick={() => showOptions(props.postId)}>{props.likes.length}</span>
    <div id={"post" + props.postId} className="d-none likers__nav">
      <UserLikesContainer likes={props.likes}/>
    </div>
  </div>
}

export default PostLikeItem