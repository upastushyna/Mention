import { USER_POST_DELETED } from '../constants/action-types'

export const deletePost = postId => dispatch => {
  fetch('/api/posts/delete/' + postId,
    {
      method: 'DELETE',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
      }
    }).then(() => dispatch({ type: USER_POST_DELETED, payload: postId }))
}