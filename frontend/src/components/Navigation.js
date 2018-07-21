import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'

class Navigation extends React.Component {
  render () {
    return (
      <Fragment>
        <nav >
          <Link to="/messages">Message</Link>
          <Link to="/favorites">Favorites</Link>
          <Link to="/profile">Profile</Link>
          <Link to="/CreatePost">Create post</Link>
        </nav>
      </Fragment>
    )
  }
}

export default Navigation