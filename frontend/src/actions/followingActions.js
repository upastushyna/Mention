import {FOLLOWING_LOADED} from '../constants/action-types'

export const loadFollowing = username => dispatch => {
  fetch('/api/follow/following/' + username,
    {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.json())
    .then(data => dispatch({type: FOLLOWING_LOADED, payload: data}))
}