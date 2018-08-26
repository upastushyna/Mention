import {USER_PAGE_LOADED} from '../constants/action-types'

const initialState = [];

function userPageReducer (state = initialState, action) {
  switch (action.type) {
    case USER_PAGE_LOADED:
      return [...action.payload];
    default:
      return state
  }
}

export default userPageReducer