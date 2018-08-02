import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import logo from '../img/logo.png'
import home from '../img/home-icon.png'
import favorites from '../img/favorite-icon.png'
import profile from '../img/profile-icon.png'
import messages from '../img/message-icon.png'
import post from '../img/posts-icon.png'

class Navigation extends React.Component {
  render () {
    return (
      <Fragment>
        <div className="header-panel d-flex content-between">
          <div className="header-panel__left d-flex">
            <div className="header-panel__logo">
              <Link to="/" ><img className="header-panel__img" src={logo} alt=""/></Link>
            </div>
            <h2 className="header-panel__title">Name Component</h2>
            <form action="" className="header-panel__search">
              <input type="text" className="header-panel__input" placeholder="Search here people or pages..."/>
              <input type="submit" className="header-panel__btn" value="Search"/>
            </form>
          </div>
          <div className="header-panel__right"></div>
        </div>
        <div className="navigation">
          <ul className="navigation__list">
            <li className="navigation__item">
              <Link to="/"><img className="navigation__img" src={home} alt=""/></Link>
            </li>
            <li className="navigation__item">
              <Link to="/profile"><img className="navigation__img" src={profile} alt=""/></Link>
            </li>
            <li className="navigation__item">
              <Link to="/createPost"><img className="navigation__img" src={post} alt=""/></Link>
            </li>
            <li className="navigation__item">
              <Link to="/favorites"><img className="navigation__img" src={favorites} alt=""/></Link>
            </li>
            <li className="navigation__item">
              <Link to="/messages"><img className="navigation__img" src={messages} alt=""/></Link>
            </li>
          </ul>
        </div>
      </Fragment>
    )
  }
}

export default Navigation



{/*<nav className="navigation__menu d-flex">
              <Link to="/messages" className="navigation__menu--link d-flex">
                <img className="navigation__menu--img" src={message} alt=""/>Message</Link>
              <Link to="/favorites" className="navigation__menu--link d-flex">
                <img className="navigation__menu--img" src={favorite} alt=""/>Favorites</Link>
              <Link to="/profile" className="navigation__menu--link d-flex">
                <img className="navigation__menu--img" src={profile} alt=""/>Profile</Link>
              <Link to="/createPost" className="navigation__menu--link d-flex">
                <img className="navigation__menu--img" src={post} alt=""/>Create post</Link>
            </nav>*/}