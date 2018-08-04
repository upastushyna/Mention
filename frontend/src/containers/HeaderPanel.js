import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import logo from '../img/header-panel/logo.png'
import event from '../img/header-panel/calendar-icon.png'
import chat from '../img/header-panel/chat-icon.png'
import notification from '../img/header-panel/notification-icon.png'
import user from '../img/header-panel/user-img.png'

class HeaderPanel extends React.Component {
  render () {
    return (
      <Fragment>
        <div className="header d-flex content-between">
          <div className="header__left d-flex">
            <div className="logo">
              <Link to="/" ><img className="logo__img" src={logo} alt=""/></Link>
            </div>
            <h2 className="header__title">Component name</h2>
            <form action="" className="search">
              <input type="text" className="search__input search__input--non-line" placeholder="Search here people or pages..."/>
              <input type="submit" className="search__btn search__input--non-line" value="Search"/>
            </form>
          </div>
          <div className="header__right d-flex">
            <div className="header__menu">
              <ul className="header__list d-flex">
                <li className="header__item">
                  <Link to="/">
                    <img src={event} alt="" className="header__icon"/>
                  </Link>
                </li>
                <li className="header__item">
                  <Link to="/">
                    <img src={chat} alt="" className="header__icon"/>
                  </Link>
                </li>
                <li className="header__item">
                  <Link to="/">
                    <img src={notification} alt="" className="header__icon"/>
                  </Link>
                </li>
              </ul>
            </div>
            <div className="header__profile d-flex">
              <img src={user} alt="" className="header__profile-img"/>
              <h2 className="header__profile-name">Mykhail Hryhoriev</h2>
              <div className="header__profile-active"/>
              <span className="header__profile-arrow">&#711;</span>
            </div>
          </div>
        </div>
      </Fragment>
    )
  }
}

export default HeaderPanel