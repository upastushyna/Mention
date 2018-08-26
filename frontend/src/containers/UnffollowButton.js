import React, { Fragment } from 'react'

const UnffollowButton = props => {
  return <Fragment>
    <button onClick={() => props.unfollow(props.followedUser)} className="follow-button follow-button_red">
     Unfollow
    </button>
  </Fragment>
}

export default UnffollowButton