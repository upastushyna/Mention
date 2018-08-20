import { SEARCH_USERS_LOADED } from '../constants/action-types'

export const loadSearchUsers = input => dispatch => {
  fetch('/api/user/search/' + input,
    {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: SEARCH_USERS_LOADED, payload: data || []}))
}