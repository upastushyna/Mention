import {FEED_NOT_LOADED} from '../constants/action-types'

const initialState = true;

function notEmptyReducer (state = initialState, action) {
  switch (action.type) {
    case FEED_NOT_LOADED:
      return false;
    default:
      return state
  }
}

export default notEmptyReducer