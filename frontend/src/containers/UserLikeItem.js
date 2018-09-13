import React from 'react'
import {Link} from "react-router-dom";

const UserLikeItem = props =>
    <div className="follow-container">      
        <Link to={'/user/' + props.user.username} className="d-flex-center">
          <img src={props.user.profile.avatarUrl} alt="avatar" className="profile-info__avatar"/>
          <h2 className="profile-info__username">{props.user.username}</h2>
        </Link>
    </div>

export default UserLikeItem