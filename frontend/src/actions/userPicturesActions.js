import { USER_PICTURES_LOADED } from "../constants/action-types";

export const loadUser = username => dispatch => {
  fetch('/api/user/' + username ,
    {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      }
    }).then(res=>res.json())
    .then(data => dispatch({type:USER_PICTURES_LOADED, payload: data}))
}