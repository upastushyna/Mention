import React, { Fragment } from 'react'
import '../css/index.css'
import send from '../img/chat/send.png'

const Chat = props => {
  const addMessage = () => fetch('/api/messages/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({content: document.getElementById('messageInput').value,
        sender: {id: props.currentUser.id},
        receiver: {id: props.chat.user2.username === props.user2
          ? props.chat.user2.id : props.chat.user1.id},
        chat: {id: props.chat.id}})
    }).then(() => props.loadChat(props.user1, props.user2))
    .then(() => props.loadData(props.user1))
    .then(() => document.getElementById('messageInput').value = '')

  if (!props.chat || !props.chat.messages) {
    props.loadChat(props.user1, props.user2)
    return 'loading...'
  }

  return <Fragment>
    <div className="messages-container__header">
      <div className="d-flex items-center">
        <img src={props.chat.user2.username === props.user2
          ? props.chat.user2.profile.avatarUrl
          : props.chat.user1.profile.avatarUrl}
        alt="avatar" className="profile-info__avatar"/>
        <div className="profile-info__signature">
          <h2 className="profile-info__username">{props.user2}</h2>
          <span className="profile-info__alias">Online</span>
        </div>
      </div>
    </div>
    <div className="messages-container__body">
      <div className="flex-column">
      {props.chat.messages.map(message =>
        message.sender.username === props.user2
          ?        
            <div className="message message_sent">
              <img src={props.chat.user2.username === props.user2
                ? props.chat.user2.profile.avatarUrl
                : props.chat.user1.profile.avatarUrl} alt="avatar" className="profile-info__avatar"/>
              <p className="message__text message__text_sent">{message.content}</p>
              <span className="message__time">{message.timestamp.substring(11, 19)}</span>
            </div>
            /* {<span className="message-send__time">{message.timestamp.substring(0, 10)}</span> }*/
         
          :       
            <div className="message message_received">
              <span className="message__time">{message.timestamp.substring(11, 19)}</span>
              <p className="message__text message__text_received">{message.content}</p>
              <img src={props.chat.user2.username === props.user2
                ? props.chat.user1.profile.avatarUrl
                : props.chat.user2.profile.avatarUrl} alt="avatar" className="profile-info__avatar"/>
            </div>
         
      )}
      <div id="scroller" style={{ float: 'left', clear: 'both' }}>
      </div>
      </div>
    </div>
    <div className="message-sender">
      <textarea id="messageInput" className="message-sender__input"
        placeholder="Say hi to your friend :)" maxLength={1000}/>   
        <img onClick={() => addMessage()} src={send} alt="icon-send-msg" className="message-sender__actions"/>
    </div>
  </Fragment>
}

export default Chat