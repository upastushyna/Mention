import React, {Fragment} from 'react'
import {Link} from 'react-router-dom'
import {getDateFromDb} from '../js/chatdisplaytime'

const ChatsContainer = props =>
  props.chats.map(chat =>
    <Fragment>
      <Link className="chat-container" to={chat.user1.username === props.username
        ? '/messages/' + chat.user2.username : '/messages/' + chat.user1.username} onClick={() => props.loadChat(chat.user1.username, chat.user2.username)}>
        <div className="d-flex items-center">
          <img src={chat.user1.username === props.username
            ? chat.user2.profile.avatarUrl : chat.user1.profile.avatarUrl} alt="icon-search" className="profile-info__avatar"/>
          <div className="profile-info__signature">
            <h2 className="profile-info__username color-dark-grey">{chat.user1.username === props.username
              ? chat.user2.username : chat.user1.username}</h2>
            <span className="profile-info__alias">
              {chat.messages.length !== 0
                ? getDateFromDb(chat.messages[chat.messages.length - 1].timestamp) : ''}
            </span>
          </div>
        </div>
        <p className="chat-container__text">{chat.messages.length !== 0
          ? chat.messages[chat.messages.length - 1].content.substring(0, 35) : ''}</p>

      </Link>
    </Fragment>
  )

export default ChatsContainer