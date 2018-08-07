import {combineReducers} from 'redux';
import feedReducer from './feedReducer'
import userPageReducer from "./userPageReducer";
import chatReducer from "./chatReducer";


export default combineReducers({
  feed: feedReducer,
  userPosts: userPageReducer,
  chats: chatReducer
})