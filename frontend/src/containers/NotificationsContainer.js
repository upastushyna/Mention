import React from 'react'
import EmptyState from "./EmptyState";
import NotificationItem from "./NotificationItem";
import notification from '../img/notification.svg'

const NotificationsContainer = props => {
  if (props.notifications.length === 0) {
    return <EmptyState title="No new notifications"
                       message="Looks like there's nothing new to show" image={notification}/>
  }

  return props.notifications.map(notification =>
   
    <NotificationItem notification={notification}
                      loadNotifications={props.loadNotifications}
                      checkRead={props.checkRead}/>)
};

export default NotificationsContainer