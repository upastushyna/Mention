import {USER_POST_DELETED, POST_LOADED} from '../constants/action-types'

export const deletePost = (data) => dispatch => {
  fetch('/api/posts/delete',
    {
      method: 'DELETE',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({id: data.id})
    }).then(() => dispatch({ type: USER_POST_DELETED, payload: data.id }))
    .then(() => data.loadPosts(data.username))
};

export const loadPostById = id => dispatch => {
  fetch('/api/posts/get/' + id,
    {
      method: 'GET',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => dispatch({type: POST_LOADED, payload: data || {}}))
};
