import React, { Fragment } from 'react'
import avatar from '../img/header-panel/user-img.png'
import {ID} from "../constants/hardcode";

const AddComment = props => {

  const addComment = () => fetch('/api/comments/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({body:document.getElementById('commentInput' + props.postId)
          .value, commentator:{id:ID}, post:{id:props.postId}})
    }).then(() => props.loadData(props.username))
    .then(() => document.getElementById('commentInput' + props.postId).value = "");

  return <Fragment>
    <div className="add-comment">
      <div className="add-comment__form d-flex items-center">
        <img src={avatar} alt="" className="add-comment__avatar"/>
        <textarea className="add-comment__input"
                  id={"commentInput" + props.postId} placeholder="Share your thoughts"
                  maxLength={255}/>
        <button onClick={() => addComment()} className="add-comment__button color-white" value="Post Comment"/>
      </div>
    </div>
  </Fragment>;
}

export default AddComment