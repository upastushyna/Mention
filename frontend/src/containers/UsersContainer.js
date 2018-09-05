import React from 'react'
import UserItem from './UserItem'

const UsersContainer = props => {

  return props.users.map(user =>
    <UserItem currentUser={props.currentUser}
      follow={props.follow}
      unfollow={props.unfollow}
      user={user}/>)
}
export default UsersContainer