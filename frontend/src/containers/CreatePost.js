import React, { Fragment } from 'react'

const CreatePost = props => {
  return <Fragment>
    <div className="create-post">
      <textarea id="postInput" placeholder="Share your thoughts" ref="postInput" maxLength={255}/>
      <button>Add new post</button>
    </div>
  </Fragment>
}

export default CreatePost