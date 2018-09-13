import React, { Fragment } from 'react'
import Loader from './Loader'

const HeaderProfile = props => {
  if (!props.user || !props.user.username) {
    return <Loader/>
  }

  return <Fragment>
   <div style={ { backgroundImage: `url(${props.user.profile.backgroundUrl})` } } className="header-profile">
      <div className="header-profile__user">
        <img src={props.user.profile.avatarUrl} alt="avatar" className="header-profile__img"/>
        <h3 className="profile-info__username">{props.user.username}</h3>
      </div>
    </div>
  </Fragment>
};
export default HeaderProfile