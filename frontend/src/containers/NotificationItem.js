import React from 'react'
import {getDateFromDb} from "../js/timestamp";

const NotificationItem = props => {

  return <p>{"New " + props.notification.type.toLowerCase() +
  " from " + props.notification.sender.username +
  " " + getDateFromDb(props.notification.modifyTimestamp)}</p>
};
export default NotificationItem