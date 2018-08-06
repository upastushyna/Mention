import React, { Fragment } from 'react'

const Comments = props =>
  props.comments.map(comment =>
  <Fragment>
    <img src={comment.commentator.profile.avatarUrl}/>
    <h2>{comment.commentator.username}</h2>
    <p>{comment.body}</p>
    <h2>{comment.timestamp}</h2>
  </Fragment>)

export default Comments