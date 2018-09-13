import React from 'react'
import Loader from "./Loader";
import NotificationItem from "./NotificationItem";

const NotificationsContainer = props => {
  if (props.notifications.length === 0) {
    return <Loader/>
  }
  return props.notifications.map(notification =>
    <NotificationItem notification={notification}/>)
}
export default NotificationsContainer