import React from 'react'
import UnffollowButton from './UnffollowButton'
import FollowButton from './FollowButton'

const UserItem = props =>
  <div className="follow-container">
    <div className="d-flex items-center">
      <img src={props.user.profile.avatarUrl} alt="avatar" className="profile-info__avatar"/>
      <div className="profile-info__signature">
        <h2 className="profile-info__username color-dark-grey">{props.user.username}</h2>
        <span className="profile-info__alias">developer</span>
      </div>
    </div>
    <div className="d-flex">
      {props.currentUser.followedUsers.find(follow =>
        follow.followedUser.id === props.user.id)?
        <UnffollowButton unfollow={props.unfollow} followedUser={props.user.id}/> :
        <FollowButton follow={props.follow} followedUser={props.user.id}/>
      }
    </div>
  </div>;

export default UserItem