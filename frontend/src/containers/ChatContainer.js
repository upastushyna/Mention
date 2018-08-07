import React, { Fragment } from 'react'
import {Link, Route, Switch} from 'react-router-dom';
import ChatItem from './ChatItem'

const ChatContainer = props =>
  props.chats.map(chat =>
    <Link to={chat.user1.username === props.username?
      '/messages/' + chat.user2.username:'/messages/' + chat.user1.username}>
      <img src={chat.user1.username === props.username?
        chat.user2.profile.avatarUrl:chat.user1.profile.avatarUrl}/>
      <h2>{chat.user1.username === props.username?
        chat.user2.username:chat.user1.username}</h2>
      <p>{chat.messages[chat.messages.length - 1].content}</p>
    </Link>
  )


export default ChatContainer