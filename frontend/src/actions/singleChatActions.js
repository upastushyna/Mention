import {CHAT_LOADED} from "../constants/action-types";

export const loadChat = (username1, username2) => dispatch => {
  fetch('/api/posts/c/user1=' + username1 + '&user2=' + username2 ,
    {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      }
    }).then(res=>res.json())
    .then(data => dispatch({type:CHAT_LOADED, payload: data}))
}