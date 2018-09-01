import { USER_COMMENT_DELETED } from '../constants/action-types'

export const deleteComment = commentId => dispatch => {
  fetch('/api/comments/delete',
    {
      method: 'DELETE',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({id: commentId})
    }).then(() => dispatch({ type: USER_COMMENT_DELETED, payload: commentId }))
}