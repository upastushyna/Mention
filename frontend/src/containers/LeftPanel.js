import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import home from '../img/left-panel/home-icon.png'
import favorites from '../img/left-panel/favorite-icon.png'
import profile from '../img/left-panel/profile-icon.png'
import messages from '../img/left-panel/message-icon.png'
import post from '../img/left-panel/posts-icon.png'

class LeftPanel extends React.Component {
  render () {
    return (
      <Fragment>
        <div className="navigation">
          <ul className="navigation__menu">
            <li className="navigation__item">
              <Link to="/">
                <img className="navigation__img" src={home} alt=""/>
                <h4 className="navigation__hover">home</h4>
              </Link>
            </li>
            <li className="navigation__item">
              <Link to="/profile">
                <img className="navigation__img" src={profile} alt=""/>
                <h4 className="navigation__hover">profile</h4>
              </Link>
            </li>
            <li className="navigation__item">
              <Link to="/createPost">
                <img className="navigation__img" src={post} alt=""/>
                <h4 className="navigation__hover">post</h4>
              </Link>
            </li>
            <li className="navigation__item">
              <Link to="/favorites">
                <img className="navigation__img" src={favorites} alt=""/>
                <h4 className="navigation__hover">favorites</h4>
              </Link>
            </li>
            <li className="navigation__item">
              <Link to="/messages">
                <img className="navigation__img" src={messages} alt=""/>
                <h4 className="navigation__hover">messages</h4>
              </Link>
            </li>
          </ul>
        </div>
      </Fragment>
    )
  }
}

export default LeftPanel