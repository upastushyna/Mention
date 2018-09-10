import {POST_LOADED, USER_POST_DELETED} from '../constants/action-types'

const initialState = {};

function postsReducer (state = initialState, action) {
  switch (action.type) {
    case USER_POST_DELETED:
      return state.filter((item) => (item.id !== action.id));
    case POST_LOADED:
      return action.payload;
    default:
      return state
  }
}

export default postsReducer