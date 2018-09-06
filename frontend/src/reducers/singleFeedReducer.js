import {SINGLE_POST_LOADED} from '../constants/action-types'

const initialState = [];

function singleFeedReducer (state = initialState, action) {
  switch (action.type) {
    case SINGLE_POST_LOADED :
      return [...action.payload, ...state];
    default:
      return state
  }
}

export default singleFeedReducer