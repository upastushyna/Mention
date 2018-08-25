import React from 'react'
import UnffollowButton from './UnffollowButton'
import FollowButton from './FollowButton'

const UserItem = props =>
  <div className="follow-container">
    <div className="d-flex-center">
      <img src={props.user.profile.avatarUrl} alt="avatar" className="profile-info__avatar"/> 
        <h2 className="profile-info__username">{props.user.username}</h2>  
    </div>
      {props.currentUser.followedUsers.find(follow =>
        follow.followedUser.id === props.user.id)?
        <UnffollowButton unfollow={props.unfollow} followedUser={props.user.id}/> :
        <FollowButton follow={props.follow} followedUser={props.user.id}/>
      }
  </div>;

export default UserItem