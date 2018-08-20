import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import logo from '../img/header-panel/logo.png'
import chat from '../img/header-panel/chat-icon.png'
import notification from '../img/header-panel/notification-icon.png'
import avatar from '../img/header-panel/user-img.png'
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
        <div className="header d-flex content-between">
          <div className="header__left d-flex">
            <div className="logo">
              <Link to="/" ><img className="logo__img" src={logo} alt=""/></Link>
            </div>
            <h2 className="header__title">mention</h2>
            <SearchContainer/>
          </div>
          <div className="header__right d-flex">
            <div className="header__menu">
              <ul className="header__list d-flex">
                <li className="header__item">
                  <Link to="/messages">
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
            <div className="profile-small pointer d-flex profile-small--position">
              <img src={this.props.currentUser.profile.avatarUrl} alt="" className="profile-small__avatar"/>
              <div className="profile-small__signature">
                <h2 className="profile-small__username color-white">
                  {this.props.currentUser.username}
                </h2>
                <span className="profile-small__alias">DEVELOPER</span>
              </div>
              <div className="profile-small__isActive"/>
              <span className="profile-small__arrow color-white">&#711;</span>
            </div>
          </div>
        </div>
        <div className="header__space" />
      </Fragment>
    )
  }
}

export default HeaderPanel