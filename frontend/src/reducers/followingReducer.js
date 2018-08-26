import {FOLLOWING_LOADED} from '../constants/action-types'

const initialState = [];

function followingReducer (state = initialState, action) {
  switch (action.type) {
    case FOLLOWING_LOADED:
      return [...action.payload];
    default:
      return state
  }
}

export default followingReducer