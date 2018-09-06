import {FEED_LOADED, USER_POST_DELETED} from '../constants/action-types'

const initialState = []

function feedReducer (state = initialState, action) {
  switch (action.type) {
    case FEED_LOADED:
      return [...action.payload]
    default:
      return state
  }
}

export default feedReducer