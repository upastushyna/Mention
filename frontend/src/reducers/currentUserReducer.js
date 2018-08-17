import {CURRENT_USER_LOADED} from "../constants/action-types";

const initialState = {};

function currentUserReducer (state = initialState, action){
  switch(action.type) {
    case CURRENT_USER_LOADED:
      return action.payload;
    default:
      return state;
  }
}

export default currentUserReducer