import React from 'react'
import { Link } from 'react-router-dom'
import closeIcon from '../img/close.svg'
import infoIcon from '../img/info-icon.png'
import {COMMENT, COMMENT_LIKE, FOLLOW, MESSAGE, POST, POST_LIKE} from "../constants/action-types";

const PopUpNotification = props => {

  const checkAndHide = (loadUnread, id) => {
    props.checkRead(loadUnread, id);
    document.getElementById('pop-up').classList.add('d-none');
  };

  if (!props.notification || !props.notification.sender) {
    return ""
  }

  return <Link id='pop-up' to={props.notification.url} className="notify"
    onClick={() => checkAndHide(props.loadUnread, props.notification.id)}>
    <img className="notify__icon" src={infoIcon} alt="close" />
    <p className="notify__text">{props.notification.message}</p>
    <img className="notify__close" src={closeIcon} alt="close" />
  </Link>
};
export default PopUpNotification