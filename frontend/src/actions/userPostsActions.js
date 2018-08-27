import { USER_POST_DELETED } from '../constants/action-types'

export const deletePost = post => dispatch => {
  fetch('/api/posts/delete',
    {
      method: 'DELETE',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: USER_POST_DELETED, payload: data || {}}))
}