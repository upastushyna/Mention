import React from 'react'
import { Link } from 'react-router-dom'
import closeIcon from '../img/close.svg'
import infoIcon from '../img/info-icon.png'

const PopUpNotification = props => {

  if (!props.notification || !props.notification.sender) {
    return ""
  }

  return <Link to={props.notification.url} className="notify"
    onClick={() => props.checkRead(props.loadUnread, props.notification.id)}>
    <img className="notify__icon" src={infoIcon} alt="close" />
    <p className="notify__text">{props.notification.message}</p>
    <img className="notify__close" src={closeIcon} alt="close" />
  </Link>
};
export default PopUpNotification