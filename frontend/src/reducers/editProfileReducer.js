import {EDIT_PROFILE_LOADED} from "../constants/action-types";

const initialState = [];

function editProfileReducer(state = initialState, action) {
  switch (action.type) {
    case EDIT_PROFILE_LOADED:
      return [...action.payload];
    default:
      return state;
  }
}

export default editProfileReducer