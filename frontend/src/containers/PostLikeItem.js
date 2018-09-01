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

  const show = () => {
    if (!document.getElementById("likers").classList.contains("d-none")) {
      hide()
    } else {
      document.getElementById("likers").classList.remove("d-none");
    }
  }

  const hide = () => {
    if (document.getElementById("likers")) {
      document.getElementById("likers").classList.add("d-none");
    }
  };

  return <div className="d-flex-center">
    {props.likes.find(like => like.user.username === props.currentUser.username)
        ? <img onClick={() => deleteLike()} src={likeFilled} alt="like" className="post__action-img"/>
        : <img onClick={() => addLike()} src={like} alt="dislike" className="post__action-img"/>}
    <span className="post__action-count" onClick={() => show()}>{props.likes.length}</span>
    <div id="likers" className="d-none likers__nav">
      <UserLikesContainer likes={props.likes}/>
    </div>
  </div>
}

export default PostLikeItem