import React from 'react'
import {Link} from "react-router-dom";

const UserLikeItem = props =>
    <div className="follow-container">
      <div className="d-flex-center">
        <Link to={'/user/' + props.user.username}>
          <img src={props.user.profile.avatarUrl} alt="avatar" className="profile-info__avatar"/>
          <h2 className="profile-info__username">{props.user.username}</h2>
        </Link>
      </div>
    </div>

export default UserLikeItem