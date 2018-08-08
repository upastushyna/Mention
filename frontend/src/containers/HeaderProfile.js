import React, { Fragment } from 'react'
import user from '../img/header-panel/user-img.png'

const HeaderProfile = props =>
      <Fragment>
        <div className="header-profile">
          <div className="header-profile__background" />
          <div className="header-profile__user">
            <img src={user} alt="" className="header-profile__img"/>
            <h3 className="header-profile__name">Mykhail Hryhoriev</h3>
            <h4 className="header-profile__location">Kyiv, Ukraine</h4>
          </div>
        </div>
      </Fragment>

export default HeaderProfile