import {LIKED_LOADED} from '../constants/action-types'

export const loadLiked = username => dispatch => {
  fetch('/api/posts/liked/' + username,
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: LIKED_LOADED, payload: data || []}))
}