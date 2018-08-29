import {EDIT_PROFILE_LOADED} from "../constants/action-types"

export const loadProfileById = id => dispatch => {
  fetch('/api/profiles/' + id,
      {
        method: 'GET',
        headers: {
          'Authorization': "Bearer " + localStorage.getItem("accessToken"),
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
      }).then(res => res.headers.get('content-type') === null ? null : res.json())
      .then(data => dispatch({type: EDIT_PROFILE_LOADED, payload: data || {}}))
}