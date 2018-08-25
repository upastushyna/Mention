import {CHAT_LOADED} from '../constants/action-types'

export const loadChat = (username1, username2) => dispatch => {
  fetch('/api/chats/c/user1=' + username1 + '&user2=' + username2,
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: CHAT_LOADED, payload: data || []}))
}
