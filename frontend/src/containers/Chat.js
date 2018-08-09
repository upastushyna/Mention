import React, { Fragment } from 'react'
import '../css/index.css'
//get sender's username: message.sender.username
//get sender's avatar": message.sender.profile.avatarUrl
//get message's timestamp: message.timestamp


const Chat = props => {
  if (!props.chat || !props.chat.messages){
    props.loadChat(props.user1, props.user2);
    return "loading..."
  }

  return <Fragment>
        {props.chat.messages.map(message =>
          message.sender.username === props.user1?
        <div className="message-sent">
          <p className="message-sent__text">{message.content}</p>
        </div>
         :
        <div className="message-come">
          <p className="message-come__text">{message.content}</p>
        </div>)}
      </Fragment>
};

export default Chat