import {ALL_NOTIFICATIONS_LOADED} from '../constants/action-types'

const initialState = [];

function allNotificationsReducer (state = initialState, action) {
  switch (action.type) {
    case ALL_NOTIFICATIONS_LOADED:
      return [...action.payload];
    default:
      return state
  }
}

export default allNotificationsReducer