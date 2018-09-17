import React from 'react'
import PostItem from './PostItem'
import Loader from "./Loader";
import EmptyState from "./EmptyState";
import searchIcon from '../img/search-icon.svg'

const PostsContainer = props => {
  if (props.userPosts.length === 0) {
    return (<div className="empty-loader">
      <Loader/>
      <EmptyState image={searchIcon} title="Oops! Nothing has been found :(" message={'Please, try another search query'}/>
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