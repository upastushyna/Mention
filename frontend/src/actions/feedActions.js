import { FEED_LOADED } from "../constants/action-types";

export const loadFeed = id => dispatch => {
  fetch('/api/followed/' + id ,
    {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      }
    }).then(res=>res.json())
    .then(data => dispatch({type:FEED_LOADED, payload: data}))
}