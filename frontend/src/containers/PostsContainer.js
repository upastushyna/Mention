import React from 'react'
import PostItem from './PostItem'

const PostsContainer = props => {
  if (props.userPosts.length === 0) {
    return <p>Nothing to show yet! Follow other users and share your thoughts to see what's happening</p>
  }
  return props.userPosts.map(post =>
    <PostItem username={props.username}
      loadData={props.loadData}
      post={post}
      currentUser={props.currentUser}
      deletePost={props.deletePost}/>)
}
export default PostsContainer