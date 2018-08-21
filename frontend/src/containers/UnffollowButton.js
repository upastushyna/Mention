import React, { Fragment } from 'react'

const UnffollowButton = props => {
  return <Fragment>
    <div onClick={() => props.unfollow()} className="unfollow-button left-margin">
      <span className="unfollow-button__text">Unfollow</span>
    </div>
  </Fragment>
}

export default UnffollowButton