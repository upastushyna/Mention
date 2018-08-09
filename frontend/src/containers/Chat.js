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
            <div className="message-body">
              <div className="clear"></div>
              <div className="message-sent d-flex items-center">
                <img src={props.chat.user1.profile.avatarUrl} alt="" className="profile-small__avatar"/>
                <p className="message-sent__text">{message.content}</p>
              </div>
            </div>
         :
            <div className="message-body">
              <div className="clear"></div>
              <div className="message-come d-flex items-center">
                <p className="message-come__text">{message.content}</p>
                <img src={props.chat.user2.profile.avatarUrl} alt="" className="profile-small__avatar"/>
              </div>
            </div>
        )}
      </Fragment>
};

export default Chat