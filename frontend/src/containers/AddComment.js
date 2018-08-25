import React, { Fragment } from 'react'

const AddComment = props => {
  const addComment = () => fetch('/api/comments/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({body: document.getElementById('commentInput' + props.postId)
        .value,
      commentator: {id: props.currentUser.id},
      post: {id: props.postId}})
    }).then(() => props.loadData(props.username))
    .then(() => document.getElementById('commentInput' + props.postId).value = '')

  return <Fragment>
   
      <div className="add-comment">
        <img src={props.currentUser.profile.avatarUrl} alt="avatar" className="add-comment__avatar"/>
        <textarea className="create-post__input"
          id={'commentInput' + props.postId} placeholder="Add comment"
          maxLength={280}/>
          <button onClick={() => addComment()} className="create-post__btn">Add</button>
          <button onClick={() => addComment()} className="create-post__btn create-post__btn_rounded">+</button>
      </div>
    
    
  </Fragment>
}

export default AddComment