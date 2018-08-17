import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import feed from '../img/left-panel/feed-icon.png'
import profile from '../img/left-panel/profile-icon.png'
import messages from '../img/left-panel/message-icon.png'

class LeftPanel extends React.Component {
  render () {
    return (
      <Fragment>
        <div className="navigation">
          <ul className="navigation__menu">
            <li className="navigation__item">
              <Link to="/">
                <img className="navigation__img" src={feed} alt=""/>
                <h4 className="navigation__hover">feed</h4>
              </Link>
            </li>
            <li className="navigation__item">
              <Link to="/yarik">
                <img className="navigation__img" src={profile} alt=""/>
                <h4 className="navigation__hover">profile</h4>
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