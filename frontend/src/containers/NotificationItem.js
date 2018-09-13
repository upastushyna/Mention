import React from 'react'
import {getDateFromDb} from "../js/timestamp";
import {Link} from 'react-router-dom'
import {COMMENT, COMMENT_LIKE, FOLLOW, MESSAGE, POST, POST_LIKE, REPOST} from "../constants/action-types";

const NotificationItem = props => {
  let url;
  const {notification} = props;
  switch (notification.type) {
    case MESSAGE:
      url = `/messages/${notification.sender.username}`;
      break;
    case COMMENT:
      url = `/post/${notification.post.id}`;
      break;
    case POST:
      url = `/post/${notification.post.id}`;
      break;
    case FOLLOW:
      url = `/user/${notification.sender.username}`;
      break;
    case POST_LIKE:
      url = `/post/${notification.post.id}`;
      break;
    case COMMENT_LIKE:
      url = `/post/${notification.post.id}`;
      break;
    case REPOST:
      url = `/post/${notification.post.id}`;
  }

  return <Link to={url}><p>{props.notification.type.toLowerCase() +
  " from " + props.notification.sender.username +
  " " + getDateFromDb(props.notification.modifyTimestamp)}</p></Link>
};
export default NotificationItem