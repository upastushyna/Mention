import { USER_POST_DELETED } from '../constants/action-types'

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
    }).then(() => dispatch({ type: USER_POST_DELETED, payload: data[0] }))
    .then(() => data.loadPosts(data.username))
};