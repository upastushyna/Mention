import React from 'react'
import PostItem from './PostItem'

const PostsContainer = props =>
  props.userPosts.map(post =>
    <PostItem username={props.username}
      loadData={props.loadData}
      post={post}
      currentUser={props.currentUser}/>)

export default PostsContainer
