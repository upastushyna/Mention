import React, {Fragment} from 'react'
import {Link} from 'react-router-dom';

const ChatsContainer = props =>
  props.chats.map(chat =>
      <Fragment>
        <Link className="message-container__link" to={chat.user1.username === props.username?
            '/messages/' + chat.user2.username:'/messages/' + chat.user1.username}>
          <div className="message-container"
               onClick={() => props.loadChat(chat.user1.username, chat.user2.username)}>
            <div className="profile-small d-flex">
              <img src={chat.user1.username === props.username?
                  chat.user2.profile.avatarUrl:chat.user1.profile.avatarUrl} alt="" className="profile-small__avatar"/>
              <div className="profile-small__signature">
                <h2 className="profile-small__username color-dark-grey">{chat.user1.username === props.username?
                    chat.user2.username:chat.user1.username}</h2>
                <span className="profile-small__alias">{chat.messages[chat.messages.length - 1].timestamp}</span>
              </div>
            </div>
            <p className="message-container__text">{chat.messages[chat.messages.length - 1].content}</p>
          </div>
        </Link>
      </Fragment>
  )


export default ChatsContainer