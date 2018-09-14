import {ALL_NOTIFICATIONS_LOADED, UNREAD_NOTIFICATIONS_LOADED} from '../constants/action-types'

export const loadAllNotifications = () => dispatch => {
  fetch('/api/notifications',
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: ALL_NOTIFICATIONS_LOADED, payload: data || []}))
};

export const loadUnreadNotifications = () => dispatch => {
  fetch('/api/notifications/unread',
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: UNREAD_NOTIFICATIONS_LOADED, payload: data || []}))
};

export const checkReadNotification = (callback, id) => {
  fetch('/api/notifications/update',
    {
      method: 'PUT',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({id:id})
    }).then(() => callback())
};

export const checkReadAll = callback => {
  fetch('/api/notifications/updateAll',
    {
      method: 'PUT',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => callback())
};