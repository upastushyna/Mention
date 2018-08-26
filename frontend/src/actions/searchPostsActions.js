import { SEARCH_POSTS_LOADED } from '../constants/action-types'

export const loadSearchPosts = input => dispatch => {
  fetch('/api/posts/search/' + input,
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: SEARCH_POSTS_LOADED, payload: data || []}))
}