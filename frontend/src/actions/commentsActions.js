import { USER_COMMENT_DELETED } from '../constants/action-types'

export const deleteComment = (data) => dispatch => {
  fetch('/api/comments/delete',
    {
      method: 'DELETE',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({id: data.id})
    }).then(() => dispatch({ type: USER_COMMENT_DELETED, payload: data.id }))
    .then(() => data.loadPosts(data.username))
}