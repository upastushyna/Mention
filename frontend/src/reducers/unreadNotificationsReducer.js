import {UNREAD_NOTIFICATIONS_LOADED} from '../constants/action-types'

const initialState = [];

function unreadNotificationsReducer (state = initialState, action) {
  switch (action.type) {
    case UNREAD_NOTIFICATIONS_LOADED:
      return [...action.payload];
    default:
      return state
  }
}

export default unreadNotificationsReducer