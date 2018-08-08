import {CHATS_LOADED} from "../constants/action-types";

const initialState = [];

function chatReducer(state = initialState, action) {
  switch (action.type) {
    case CHATS_LOADED:
      return [...action.payload];
    default:
      return state;
  }
}

export default chatReducer