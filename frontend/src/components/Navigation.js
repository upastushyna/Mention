import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import logo from '../img/logo.png'
import home from '../img/home-icon.png'
import favorites from '../img/favorite-icon.png'
import profile from '../img/profile-icon.png'
import messages from '../img/message-icon.png'
import post from '../img/posts-icon.png'
import event from '../img/calendar-icon.png'
import chat from '../img/chat-icon.png'
import notification from '../img/notification-icon.png'
import user from '../img/user-img.png'

class Navigation extends React.Component {
  render () {
    return (
      <Fragment>
        <div className="header-panel d-flex content-between">
          <div className="header-panel__left d-flex">
            <div className="header-panel__logo">
              <Link to="/" ><img className="header-panel__img" src={logo} alt=""/></Link>
            </div>
            <h2 className="header-panel__title">Component name</h2>
            <form action="" className="header-panel__search">
              <input type="text" className="header-panel__input" placeholder="Search here people or pages..."/>
              <input type="submit" className="header-panel__btn" value="Search"/>
            </form>
          </div>
          <div className="header-panel__right d-flex">
            <div className="header-panel__menu">
              <ul className="header-panel__list d-flex">
                <li className="header-panel__item">
                  <Link to="/">
                    <img src={event} alt="" className="header-panel__icon"/>
                  </Link>
                </li>
                <li className="header-panel__item">
                  <Link to="/">
                    <img src={chat} alt="" className="header-panel__icon"/>
                  </Link>
                </li>
                <li className="header-panel__item">
                  <Link to="/">
                    <img src={notification} alt="" className="header-panel__icon"/>
                  </Link>
                </li>
              </ul>
            </div>
            <div className="header-panel__profile d-flex">
              <img src={user} alt="" className="header-panel__profile-img"/>
              <h2 className="header-panel__profile-name">Mykhail Hryhoriev</h2>
            </div>
          </div>
        </div>
        <div className="navigation">
          <ul className="navigation__list">
            <li className="navigation__item">
              <Link to="/">
                <img className="navigation__img" src={home} alt=""/>
                <h4 className="navigation__hover-name">home</h4>
              </Link>
            </li>
            <li className="navigation__item">
              <Link to="/profile">
                <img className="navigation__img" src={profile} alt=""/>
                <h4 className="navigation__hover-name">profile</h4>
              </Link>
            </li>
            <li className="navigation__item">
              <Link to="/createPost">
                <img className="navigation__img" src={post} alt=""/>
                <h4 className="navigation__hover-name">post</h4>
              </Link>
            </li>
            <li className="navigation__item">
              <Link to="/favorites">
                <img className="navigation__img" src={favorites} alt=""/>
                <h4 className="navigation__hover-name">favorites</h4>
              </Link>
            </li>
            <li className="navigation__item">
              <Link to="/messages">
                <img className="navigation__img" src={messages} alt=""/>
                <h4 className="navigation__hover-name">messages</h4>
              </Link>
            </li>
          </ul>
        </div>
      </Fragment>
    )
  }
}

export default Navigation