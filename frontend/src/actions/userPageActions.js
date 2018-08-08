import { USER_PAGE_LOADED } from "../constants/action-types";

export const loadPosts = username => dispatch => {
  fetch('/api/posts/' + username ,
    {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      }
    }).then(res=>res.json())
    .then(data => dispatch({type:USER_PAGE_LOADED, payload: data}))
}