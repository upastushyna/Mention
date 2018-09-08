import React from 'react'
import UserLikeItem from "./UserLikeItem";

const RepostContainer = props => {

  return props.children.map(post =>
      <UserLikeItem user={post.author}/>)
}
export default RepostContainer