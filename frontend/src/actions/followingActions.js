import {FOLLOWING_LOADED} from '../constants/action-types'

export const loadFollowing = username => dispatch => {
  fetch('/api/follow/following/' + username,
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: FOLLOWING_LOADED, payload: data || []}))
}