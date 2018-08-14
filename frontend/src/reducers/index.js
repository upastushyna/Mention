import {combineReducers} from 'redux';
import feedReducer from './feedReducer'
import userPageReducer from "./userPageReducer";
import chatsReducer from "./chatsReducer";
import singleChatReducer from "./singleChatReducer";
import followedReducer from "./followedReducer";
import followingReducer from "./followingReducer";
import likedReducer from "./likedReducer";

export default combineReducers({
  feed: feedReducer,
  userPosts: userPageReducer,
  chats: chatsReducer,
  chat: singleChatReducer,
  followed: followedReducer,
  following: followingReducer,
  likedPosts: likedReducer
})