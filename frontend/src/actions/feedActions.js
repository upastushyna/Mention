import {FEED_LOADED, FEED_NOT_LOADED, LOADING_POSTS} from '../constants/action-types'
import {startLoader, stopLoader} from "./loaderActions";

export const loadFeed = (dispatch, username, page, size, callback) => {
  dispatch(startLoader(LOADING_POSTS));
  fetch(`/api/posts/followed/${username}/${page}/${size}`,
    {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    })
    .then(res => res.headers.get('content-type') === null ? null : res.json())
    .then(data => {
      if (data.length === 0) {
        dispatch({type: FEED_NOT_LOADED});
        dispatch(stopLoader(LOADING_POSTS))
      } else {
        dispatch({type: FEED_LOADED, payload: data});
        dispatch(stopLoader(LOADING_POSTS));
        callback && callback();
      }
    });
};