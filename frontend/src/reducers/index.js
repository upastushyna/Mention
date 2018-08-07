import {combineReducers} from 'redux';
import feedReducer from './feedReducer'
import userPageReducer from "./userPageReducer";


export default combineReducers({
  feed: feedReducer,
  userPosts: userPageReducer
})