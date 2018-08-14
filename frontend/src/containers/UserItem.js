import React from 'react'

const UserItem = props =>
  <div className="profile-small pointer d-flex">
    <img src={props.user.profile.avatarUrl} alt="" className="profile-small__avatar"/>
    <div className="profile-small__signature">
      <h2 className="profile-small__username color-dark-grey">{props.user.username}</h2>
    </div>
  </div>;

export default UserItem