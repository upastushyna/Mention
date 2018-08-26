import { USER_PAGE_LOADED } from '../constants/action-types'

export const loadPosts = username => dispatch => {
  fetch('/api/posts/' + username,
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: USER_PAGE_LOADED, payload: data || []}))
}