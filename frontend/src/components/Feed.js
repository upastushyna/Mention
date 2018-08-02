import React, { Fragment } from 'react'
import PostItem from "../containers/PostItem"
export default class Post extends React.Component {
  render () {
    const posts = this.getPosts().map(post =>
    <PostItem avatar={post.user.profile.avatarUrl} username={post.user.username} body={post.body}/>)
    return (
      <Fragment>
        {posts}
      </Fragment>
    )
  }
}