import {SEARCH_USERS_LOADED} from '../constants/action-types'

const initialState = [];

function searchUsersReducer (state = initialState, action) {
  switch (action.type) {
    case SEARCH_USERS_LOADED:
      return [...action.payload];
    default:
      return state
  }
}

export default searchUsersReducer