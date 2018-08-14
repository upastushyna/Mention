import {FOLLOWED_LOADED} from "../constants/action-types";

const initialState = [];

function followedReducer (state = initialState, action){
  switch(action.type) {
    case FOLLOWED_LOADED:
      return [...action.payload];
    default:
      return state;
  }
}

export default followedReducer