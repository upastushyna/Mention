import React from 'react'
import UnffollowButton from "./UnffollowButton";
import FollowButton from "./FollowButton";

const UserItem = props =>
  <div className="follow-container d-flex content-between items-center">
    <div className="profile-small d-flex">
      <img src={props.user.profile.avatarUrl} alt="" className="profile-small__avatar"/>
      <div className="profile-small__signature">
        <h2 className="profile-small__username color-dark-grey">{props.user.username}</h2>
        <span className="profile-small__alias">developer</span>
      </div>
    </div>
    <div className="d-flex">
      <FollowButton/>
      <UnffollowButton/>
    </div>
  </div>

export default UserItem