import React from 'react'
import {Link} from 'react-router-dom'

const PopUpNotification = props => {

  if (!props.notification || !props.notification.sender) {
    return ""
  }
  return <Link to={props.notification.url}>
    <p>{props.notification.message}</p>
  </Link>
};
export default PopUpNotification