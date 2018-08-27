import {USER_POST_DELETED} from '../constants/action-types'

const initialState = []

function postReducer (state = initialState, action) {
  switch (action.type) {
    case USER_POST_DELETED:
      return [...action.payload]
    default:
      return state
  }
}

export default postReducer