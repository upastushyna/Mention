import { CHATS_LOADED } from "../constants/action-types";

export const loadChats = username => dispatch => {
  fetch('/api/chats/' + username ,
    {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      }
    }).then(res=> res.json())
    .then(data => dispatch({type:CHATS_LOADED, payload: data}))
}