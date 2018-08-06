import {combineReducers} from 'redux';
import feedReducer from './feedReducer'


export default combineReducers({
  feed: feedReducer
})