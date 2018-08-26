import {SEARCH_POSTS_LOADED} from '../constants/action-types'

const initialState = [];

function searchPostsReducer (state = initialState, action) {
  switch (action.type) {
    case SEARCH_POSTS_LOADED:
      return [...action.payload];
    default:
      return state
  }
}

export default searchPostsReducer