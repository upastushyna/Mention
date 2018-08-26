import React, { Fragment } from 'react'

const FollowButton = props => {
  return <Fragment>
    <button onClick={() => props.follow(props.followedUser)} className="follow-button follow-button_green">
      Follow
    </button>
  </Fragment>
}

export default FollowButton