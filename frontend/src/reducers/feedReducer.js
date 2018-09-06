import {FEED_LOADED} from '../constants/action-types'

const initialState = [];

function feedReducer (state = initialState, action) {
  switch (action.type) {
    case FEED_LOADED :
      return [...state, ...action.payload];
    default:
      return state
  }
}

export default feedReducer