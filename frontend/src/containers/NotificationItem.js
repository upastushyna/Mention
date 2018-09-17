import React from 'react'
import {getDateFromDb} from "../js/timestamp";
import {Link} from 'react-router-dom'
import chat from '../img/header-panel/chat-icon.png'
import comment from '../img/post-form/comment-icon.png'
import forward from '../img/post-form/forward-icon.png'
import likeFilled from '../img/post-form/like-icon-filled.png'
import {COMMENT, COMMENT_LIKE, FOLLOW, MESSAGE, POST, POST_LIKE, REPOST} from "../constants/action-types";

const NotificationItem = props => {
  let url;
  let icon;
  const {notification} = props;
  switch (notification.type) {
    case MESSAGE:
      url = `/messages/${notification.sender.username}`;
      icon = chat;
      break;
    case COMMENT:
      url = `/post/${notification.post.id}`;
      icon = comment;
      break;
    case POST:
      url = `/post/${notification.post.id}`;
      icon = comment;
      break;
    case FOLLOW:
      url = `/user/${notification.sender.username}`;
      icon = forward;
      break;
    case POST_LIKE:
      url = `/post/${notification.post.id}`;
      icon = likeFilled;
      break;
    case COMMENT_LIKE:
      url = `/post/${notification.post.id}`;
      icon = likeFilled;
      break;
    case REPOST:
      url = `/post/${notification.post.id}`;
      icon = forward;
  }

  return <Link to={url} className="notify__list-item"
               onClick={() => props.checkRead(props.loadNotifications, props.notification.id)}>
  <div className="d-flex-center"><img className="notify__icon" src={icon}/>{props.notification.type.toLowerCase() +
  " from " + props.notification.sender.username +
  " " + getDateFromDb(props.notification.timestamp)}</div><span className="notify__arrow">&gt;</span></Link>
};
export default NotificationItem