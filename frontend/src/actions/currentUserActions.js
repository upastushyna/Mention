import {CURRENT_USER_LOADED} from "../constants/action-types";

export const loadCurrentUser = () => dispatch => {
  fetch('/api/user/current',
    {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      }
    }).then(res=>res.json())
    .then(data => dispatch({type:CURRENT_USER_LOADED, payload: data}))
}
