import {FOLLOWED_LOADED} from '../constants/action-types'

export const loadFollowed = username => dispatch => {
  fetch('/api/follow/followed/' + username,
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: FOLLOWED_LOADED, payload: data || []}))
}