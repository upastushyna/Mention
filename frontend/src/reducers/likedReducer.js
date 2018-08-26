import {LIKED_LOADED} from '../constants/action-types'

const initialState = [];

function likedReducer (state = initialState, action) {
  switch (action.type) {
    case LIKED_LOADED:
      return [...action.payload];
    default:
      return state
  }
}

export default likedReducer