import {FEED_LOADED} from '../constants/action-types'

export const loadFeed = username => dispatch => {
  fetch('/api/posts/followed/' + username,
    {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: FEED_LOADED, payload: data || []}))
}
