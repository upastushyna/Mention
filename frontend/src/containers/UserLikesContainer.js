import React from 'react'
import UserLikeItem from "./UserLikeItem";

const UserLikesContainer = props => {

  return props.likes.map(like =>
      <UserLikeItem user={like.user}/>)
}
export default UserLikesContainer