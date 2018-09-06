import {USER_COMMENT_DELETED} from '../constants/action-types'

const initialState = []

function postsReducer (state = initialState, action) {
  switch (action.type) {
    case USER_COMMENT_DELETED:
      return state.filter((item) => (item.id !== action.id))
    default:
      return state
  }
}

export default postsReducer