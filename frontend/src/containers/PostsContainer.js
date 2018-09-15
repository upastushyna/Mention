import React from 'react'
import PostItem from './PostItem'
import Loader from "./Loader";

const PostsContainer = props => {
  if (props.userPosts.length === 0) {
    return <Loader/>
  }

  return props.userPosts.map(post =>
    <PostItem username={props.username}
      loadData={props.loadData}
      post={post}
      currentUser={props.currentUser}
      deletePost={props.deletePost}
      deleteComment={props.deleteComment}
      key={post.id}/>)
}

export default PostsContainer