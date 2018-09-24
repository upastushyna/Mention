import {CURRENT_USER_LOADED} from '../constants/action-types'

export const loadCurrentUser = () => dispatch => {
  fetch('/api/user/current',
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.status !== 200 ? localStorage.removeItem('accessToken') :  res.json())
    .then(data => localStorage.getItem('accessToken')?
      dispatch({type: CURRENT_USER_LOADED, payload: data}) : window.location = '/registration')
};