import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import logo from '../img/header-panel/logo.png'
import chat from '../img/header-panel/chat-icon.png'
import notification from '../img/header-panel/notification-icon.png'
import avatar from '../img/header-panel/user-img.png'
import feed from '../img/header-panel/icon-feed.png'
import arrow from '../img/white-down-arrow.png'
import SearchContainer from './SearchContainer'

class HeaderPanel extends React.Component {
  componentWillMount () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      this.props.loadCurrentUser()
    }
  }

  render () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      return 'Loading...'
    }

    return (
      <Fragment>
        <div className="main-header">
        <div className="container main-header-wrapper">
          <div className="d-flex items-center">
              <Link to="/" className="main-header__logo"><img className="main-header__logo-img" src={logo} alt="logo-img"/>
              <h2 className="main-header__title">ention</h2>
              </Link>

            <SearchContainer/>
          </div>

             <ul className="header-menu d-flex">
                <li className="header-menu__item">
                  <Link to="/messages" className="header-menu__link">
                    <img src={chat} alt="messages" className="header-menu__icon"/>
                    <p>Messages</p>
                  </Link>
                </li>
                <li className="header-menu__item">
                  <Link to="/" className="header-menu__link">
                    <img src={notification} alt="notify" className="header-menu__icon"/>
                    <p>Notifications</p>
                  </Link>
                </li>
                <li className="header-menu__item">
                  <Link to="/" className="header-menu__link header-menu__link">
                    <img src={feed} alt="feed" className="header-menu__icon header-menu__icon_small"/>
                    <p>Feed</p>
                  </Link>
                </li>
              </ul>
       <div className="d-flex items-center">
          <Link to={"/" + this.props.currentUser.username} className="d-flex items-center">
          <img src={this.props.currentUser.profile.avatarUrl} alt="avatar" className="profile-card__avatar"/>
          </Link>
            <h2 className="profile-card__username">
                  {this.props.currentUser.username}
            </h2>
              <img src={arrow} alt="arrow" className="profile-info__arrow"/>
              </div>
            <ul className="profile__nav">
             <li className="profile__option"><a href="#" className="profile__link">Chats</a></li>
             <li className="profile__option"><a href="#" className="profile__link">Option 1</a></li>
             <li className="profile__option"><a href="#" className="profile__link">Option 2</a></li>
            </ul>
          </div>
        </div>
      </Fragment>
    )
  }
}

export default HeaderPanel