import React from 'react'
import EmptyState from "./EmptyState";
import NotificationItem from "./NotificationItem";

const NotificationsContainer = props => {
  if (props.notifications.length === 0) {
    return <EmptyState title="No new notifications" message="You read all of them :)"/>
  }

  return props.notifications.map(notification =>
   
    <NotificationItem notification={notification}
                      loadNotifications={props.loadNotifications}
                      checkRead={props.checkRead}/>)
};

export default NotificationsContainer