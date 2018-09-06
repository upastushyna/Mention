import React, {Fragment} from 'react'
import {Link} from 'react-router-dom'
import logo from '../img/header-panel/logo.png'
import chat from '../img/header-panel/chat-icon.png'
import notification from '../img/header-panel/notification-icon.png'
import feed from '../img/header-panel/icon-feed.png'
import arrow from '../img/white-down-arrow.png'
import SearchContainer from './SearchContainer'

class HeaderPanel extends React.Component {
  componentWillMount () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      this.props.loadCurrentUser()
    }
  }

   componentDidMount () {
    window.addEventListener('mousedown', event => this.hide(event));
  }

  hide = event => {
    if (event.target.id !== 'listItem') {
      this.hideOptions();
    }
  };

  logout = () => {
    localStorage.removeItem('accessToken');
    this.props.history.push('/registration')
  };

  showOptions = () => {
    if (!this.refs.nav.classList.contains('d-none')) {
      this.hideOptions()
    } else {
      this.refs.nav.classList.remove('d-none')
    }
  };

  hideOptions = () => {
    if (this.refs.nav) {
      this.refs.nav.classList.add('d-none')
    }
  };

  render () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      return 'Loading...'
    }

    return (
      <Fragment>
        <div className="main-header">
          <div className="container main-header-wrapper">
            <div className="d-flex-center">
              <Link to="/" className="main-header__logo"><img className="main-header__logo-img" src={logo}
                alt="logo-img"/>
              <h2 className="main-header__title">ention</h2>
              </Link>

              <SearchContainer/>
            </div>

            <ul className="header-menu d-flex-center">
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
            <div className="d-flex-center">
              <Link to={'/user/' + this.props.currentUser.username} className="d-flex-center">
                <img src={this.props.currentUser.profile.avatarUrl} alt="avatar" className="profile-card__avatar"/>
              </Link>
              <div className="d-flex-center" onClick={() => this.showOptions()}>
                <h2 className="profile-card__username">
                  {this.props.currentUser.username}
                </h2>
                <img src={arrow} alt="arrow" className="profile-info__arrow" tabIndex="1"/>
              </div>
              <ul ref="nav" id="nav" className="profile__nav d-none">
                <li id="listItem" onClick={() => this.logout()} ref="listItem" className="profile__option"><p className="profile__link">Logout</p></li>
                <li ref="listItem" id="listItem" className="profile__option"> <Link id="listItem" to="/editprofile" className="profile__link">Edit
                    Profile</Link></li>
              </ul>
            </div>
          </div>
        </div>
      </Fragment>
    )
  }
}

export default HeaderPanel