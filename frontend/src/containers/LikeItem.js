import React, { Fragment } from 'react'

const LikeItem = props =>
  <Fragment>
    <button>{props.likes.length}</button>

  </Fragment>

export default LikeItem