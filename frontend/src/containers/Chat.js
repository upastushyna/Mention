import React, { Fragment } from 'react'
import '../css/index.css'
import avatar from '../img/header-panel/user-img.png'
import send from '../img/chat/send.png'
import emoji from '../img/chat/emoji.png'
import attach from '../img/chat/attach.png'
//get sender's username: message.sender.username
//get sender's avatar": message.sender.profile.avatarUrl
//get message's timestamp: message.timestamp


const Chat = props => {
  if (!props.chat || !props.chat.messages){
    props.loadChat(props.user1, props.user2);
    return "loading..."
  }

  return <Fragment>
      <div className="messages-container__header white-background">
        <div className="profile-small d-flex">
          <img src={props.chat.user2.profile.avatarUrl} alt="" className="profile-small__avatar"/>
          <div className="profile-small__signature">
            <h2 className="profile-small__username color-dark-grey">{props.chat.user2.username}</h2>
            <span className="profile-small__alias">Online</span>
          </div>
        </div>
      </div>
      <div className="messages-container__body white-background">
        {props.chat.messages.map(message =>
          message.sender.username === props.user1?
            <div className="message-body">
              <div className="clear" />
              <div className="message-send d-flex items-center">
                <img src={props.chat.user1.profile.avatarUrl} alt="" className="profile-small__avatar"/>
                <p className="message-send__text">{message.content}</p>
                <span className="message-send__time">{message.timestamp.substring(0, 10)}</span>
              </div>
              {/*<span className="message-send__time">{message.timestamp.substring(0, 10)}</span>*/}
            </div>
         :
            <div className="message-body">
              <div className="clear" />
              <div className="message-come d-flex items-center">
                <span className="message-come__time">{message.timestamp.substring(0, 10)}</span>
                <p className="message-come__text">{message.content}</p>
                <img src={props.chat.user2.profile.avatarUrl} alt="" className="profile-small__avatar"/>
              </div>
            </div>
        )}
      </div>
    <div className="message-sender d-flex content-between white-background">
      <textarea className="message-sender__input" placeholder="Type something & press enter"/>
      <div className="message-sender_controller d-flex">
        <img src={send} alt="" className="message-sender__button"/>
        <img src={emoji} alt="" className="message-sender__emoji"/>
        <img src={attach} alt="" className="message-sender__attach"/>
      </div>
    </div>
      </Fragment>
};

export default Chat