import React from 'react'
import UserItem from './UserItem'

const UsersContainer = props => {
  if (!props.users || !props.users[0]) {
    props.loadUsers(props.username)
    return 'loading...'
  }

  return props.users.map(user =>
    <UserItem user={user}/>)
}
export default UsersContainer