import React, { Fragment } from 'react'
import Loader from './Loader'

const HeaderProfile = props => {
  if (!props.user || !props.user.username) {
    return <Loader/>
  }

  return <Fragment>
    <div className="header-profile">
      <img src={props.user.profile.backgroundUrl} alt="background" className="header-profile__background"/>
      <div className="header-profile__user">
        <img src={props.user.profile.avatarUrl} alt="avatar" className="header-profile__img"/>
        <h3 className="profile-info__username">{props.user.username}</h3>
      </div>
    </div>
  </Fragment>
};
export default HeaderProfile