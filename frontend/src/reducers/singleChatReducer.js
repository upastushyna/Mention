import {CHAT_LOADED} from '../constants/action-types'

const initialState = {};

function singleChatReducer(state = initialState, action) {
  switch (action.type) {
    case CHAT_LOADED:
      return action.payload;
    default:
      return state
  }
}

export default singleChatReducer