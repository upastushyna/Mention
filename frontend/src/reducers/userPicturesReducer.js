import {USER_PICTURES_LOADED} from "../constants/action-types";

const initialState = {};

function userPicturesReducer (state = initialState, action){
  switch(action.type) {
    case USER_PICTURES_LOADED:
      return action.payload;
    default:
      return state;
  }
}

export default userPicturesReducer