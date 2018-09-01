import React from 'react'
import PostItem from './PostItem'
import EmptyState from '../containers/EmptyState'

const PostsContainer = props => {
  if (props.userPosts.length === 0) {
    return <EmptyState title="Nothing to show yet!" message={"Follow other users and share your thoughts to see what's happening"}/>       
  }
  return props.userPosts.map(post =>
    <PostItem username={props.username}
              loadData={props.loadData}
              post={post}
              currentUser={props.currentUser}/>)
};

export default PostsContainer