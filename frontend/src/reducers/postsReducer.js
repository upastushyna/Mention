import {POST_LOADED, USER_POST_DELETED} from '../constants/action-types'

const initialState = {};

function postsReducer (state = initialState, action) {
  switch (action.type) {
    case USER_POST_DELETED:
      return null;
    case POST_LOADED:
      return action.payload;
    default:
      return state
  }
}

export default postsReducer