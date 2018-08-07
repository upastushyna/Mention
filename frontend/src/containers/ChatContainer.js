import React, { Fragment } from 'react'

const ChatContainer = props =>
  props.chats.map(chat =>
    <Fragment>
      <img src={chat.user1.username === props.username?
        chat.user2.profile.avatarUrl:chat.user1.profile.avatarUrl}/>
      <h2>{chat.user1.username === props.username?
        chat.user2.username:chat.user1.username}</h2>
      <p>{chat.messages[chat.messages.length - 1].content}</p>
    </Fragment>
  )


export default ChatContainer