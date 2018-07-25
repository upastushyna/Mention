import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import logo from '../img/logo.svg'
import profile from '../img/home-nav.png'
import message from '../img/message-nav.png'
import post from '../img/post-nav.png'
import favorite from '../img/favorite-nav.png'

class Navigation extends React.Component {
  render () {
    return (
      <Fragment>
        <section className="navigation">
          <div className="container d-flex content-between">
            <nav className="navigation__menu d-flex">
              <Link to="/messages" className="navigation__menu--link d-flex">
                <img className="navigation__menu--img" src={message} alt=""/>Message</Link>
              <Link to="/favorites" className="navigation__menu--link d-flex">
                <img className="navigation__menu--img" src={favorite} alt=""/>Favorites</Link>
              <Link to="/profile" className="navigation__menu--link d-flex">
                <img className="navigation__menu--img" src={profile} alt=""/>Profile</Link>
              <Link to="/createPost" className="navigation__menu--link d-flex">
                <img className="navigation__menu--img" src={post} alt=""/>Create post</Link>
            </nav>
            <div className="navigation__logo">
              <Link to="/"><img src={logo} alt="" className="navigation__logo--img"/></Link>
            </div>
            <div className="navigation__control">
              <input type="text" className="navigation__control--input" placeholder="Search"/>
              {/*<img src="" alt="" className="navigation__control--img"/>*/}
              <Link to="/" className="navigation__control--btn">Tweet</Link>
            </div>
          </div>
        </section>
      </Fragment>
    )
  }
}

export default Navigation