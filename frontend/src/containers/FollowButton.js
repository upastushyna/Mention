import React, { Fragment } from 'react'

const FollowButton = props => {
  return <Fragment>
    <div onClick={() => props.follow(props.followedUser)} className="follow-button">
      <span className="follow-button__text">Follow</span>
    </div>
  </Fragment>
}

export default FollowButton