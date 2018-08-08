import {combineReducers} from 'redux';
import feedReducer from './feedReducer'
import userPageReducer from "./userPageReducer";
import chatsReducer from "./chatsReducer";
import singleChatReducer from "./singleChatReducer";
import editProfileReducer from "./editProfileReducer";

export default combineReducers({
  feed: feedReducer,
  userPosts: userPageReducer,
  chats: chatsReducer,
  chat: singleChatReducer,
  editProfile: editProfileReducer
})