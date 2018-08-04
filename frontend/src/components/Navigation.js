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
        <div className="header d-flex content-between">
          <div className="header__left d-flex">
            <div className="header__logo">
              <Link to="/" ><img className="header__img" src={logo} alt=""/></Link>
            </div>
            <h2 className="header__title">Component name</h2>
            <form action="" className="search">
              <input type="text" className="search__input search__non-line" placeholder="Search here people or pages..."/>
              <input type="submit" className="search__btn search__non-line" value="Search"/>
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