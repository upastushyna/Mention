import React, {Fragment} from 'react'

const LikeItem = props =>
    <Fragment>
      <span>{props.likes.length}</span>
    </Fragment>

export default LikeItem