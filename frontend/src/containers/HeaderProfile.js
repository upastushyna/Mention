import React, { Fragment } from 'react'
import user from '../img/header-panel/user-img.png'

const HeaderProfile = props => {

  if(!props.user || !props.user.username) {
    return "loading..."
  }

  return <Fragment>
    <div className="header-profile">
      <img src={props.user.profile.backgroundUrl} className="header-profile__background"/>
      <div className="header-profile__user">
        <img src={props.user.profile.avatarUrl} alt="" className="header-profile__img"/>
        <h3 className="header-profile__name">{props.user.username}</h3>
        <h4 className="header-profile__location">Kyiv, Ukraine</h4>
      </div>
    </div>
  </Fragment>
}

export default HeaderProfile