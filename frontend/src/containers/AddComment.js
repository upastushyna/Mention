import React, {Fragment} from 'react'
import {Link} from "react-router-dom";

const AddComment = props => {
  const addComment = () => {
    if (document.getElementById('commentInput' + props.postId).value.length > 0) {
      fetch('/api/comments/add',
          {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
              'Authorization': "Bearer " + localStorage.getItem("accessToken")
            },
            body: JSON.stringify({
              body: document.getElementById('commentInput' + props.postId)
                  .value,
              commentator: {id: props.currentUser.id},
              post: {id: props.postId}
            })
          }).then(() => props.loadData(props.username))
          .then(() => document.getElementById('commentInput' + props.postId).value = '')
          .then(() => document.getElementById("commentButton" + props.postId).classList.add("inactive-button"));
    }
  }

  const activeCommentButton = (id) => {
    if (document.getElementById('commentInput' + id) &&
        document.getElementById('commentInput' + id).value.length > 0) {
      document.getElementById("commentButton" + id).classList.remove("inactive-button");
    } else {
      document.getElementById("commentButton" + id).classList.add("inactive-button");
    }
  }

  return <Fragment>
    <div className="add-comment">
      <Link to={"/user/" + props.username} className="post__link">
        <img src={props.currentUser.profile.avatarUrl} alt="avatar" className="add-comment__avatar"/>
      </Link>
      <textarea className="create-post__input"
                id={'commentInput' + props.postId} placeholder="Add comment"
                onKeyPress={(e) => {
                  (e.key === 'Enter' ? addComment() : null)
                }}
                onChange={() => activeCommentButton(props.postId)}
                maxLength={280}/>
      <button id={"commentButton" + props.postId} onClick={() => addComment()}
              className="create-post__btn btn-action inactive-button"><p className="btn-action__text">Add post</p><span className="btn-action__add">+</span>
      </button>
    </div>
  </Fragment>
}

export default AddComment