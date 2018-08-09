import React, { Fragment } from 'react'
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
        <p style={{color: 'red'}}>{message.content}</p>:
            <p style={{color: 'green'}}>{message.content}</p>)}
      </Fragment>
};

export default Chat