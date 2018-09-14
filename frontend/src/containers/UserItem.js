import React from 'react'
import UnffollowButton from './UnffollowButton'
import FollowButton from './FollowButton'
import {Link} from 'react-router-dom'

const UserItem = props =>
  <div className="follow-container">
    <Link to={'/user/' + props.user.username} className="post__link">
      <div className="d-flex-center">
        <img src={props.user.profile.avatarUrl} alt="avatar" className="profile-info__avatar"/>
        <h2 className="profile-info__username">{props.user.username}</h2>
      </div>
    </Link>
    {props.currentUser.followedUsers.find(follow =>
      follow.followedUser.id === props.user.id)
      ? <UnffollowButton unfollow={props.unfollow} followedUser={props.user.id}/>
      : <FollowButton follow={props.follow} followedUser={props.user.id}/>
    }
  </div>

export default UserItem