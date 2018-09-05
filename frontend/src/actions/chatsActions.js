import { CHATS_LOADED } from '../constants/action-types'

export const loadChats = () => dispatch => {
  fetch('/api/chats',
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: CHATS_LOADED, payload: data || []}))
};