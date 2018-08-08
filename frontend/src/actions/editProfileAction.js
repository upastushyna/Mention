import {EDIT_PROFILE_LOADED} from "../constants/action-types"

export const loadProfile = id => dispatch => {
  fetch('/api/profile/' + id,
      {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        }
      }).then(res => res.json())
      .then(data => dispatch({type: EDIT_PROFILE_LOADED, payload: data}))
}