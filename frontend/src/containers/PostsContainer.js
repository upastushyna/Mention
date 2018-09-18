import React from 'react'
import PostItem from './PostItem'
import Loader from "./Loader";
import EmptyState from "./EmptyState";

const PostsContainer = props => {
  if (props.userPosts.length === 0) {
    return (<div className="empty-loader">
      <Loader/>
      <EmptyState image={props.image}
                  title={props.title}
                  message={props.message}/>
    </div>)
  }

  return props.userPosts.map(post =>
    <PostItem username={props.username}
      loadData={props.loadData}
      post={post}
      currentUser={props.currentUser}
      deletePost={props.deletePost}
      deleteComment={props.deleteComment}
      key={post.id}/>)
};
                           
export default PostsContainer