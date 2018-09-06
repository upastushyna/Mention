import {LOADING_POSTS} from "../constants/action-types";

const initialState = {
  loadingPosts: false
};

function loaderReducer(state = initialState, action) {
  switch (action.types) {
    case LOADING_POSTS:
      return {...state, loadingPosts:action.payload}
    default:
      return state
  }

}

export default loaderReducer