import React, { Fragment } from 'react'
import {Route, Switch} from 'react-router-dom'
import Navigation from './Navigation'
import {loadChat} from '../actions/singleChatActions'
import ChatsContainer from '../containers/ChatsContainer'
import {loadChats} from '../actions/chatsActions'
import {connect} from 'react-redux'
import Chat from '../containers/Chat'
import search from '../img/search-icon.svg'
import {webSocketChat} from "../js/wsConnection";

class Messages extends React.Component {
  scrollToBottom = () => {
    if (document.getElementById('scroller')) {
      document.getElementById('scroller').scrollIntoView()
    }
  };

  componentWillMount () {
    if (this.props.chats.length === 0 && this.props.currentUser.username) {
      this.props.loadData()
    }
  }

  componentDidMount () {
    webSocketChat(this.props.loadMessages, this.props.loadData);
    this.scrollToBottom();
  }

  componentDidUpdate () {
    this.scrollToBottom();
    this.openChat();

  }

  openChat = () => {
    let id = document.getElementById("root");
    let messages = document.querySelector('.messages-container');
    let chatList = document.querySelector('.chats__list');

    if (id.clientWidth <= 768 && !messages.classList.contains('d-block')) {
      messages.classList.add('d-block');
      messages.classList.add('w-100');
      chatList.classList.add('d-none');
    }
  };

  addChat = () => fetch('/api/chats/add',
    {
      method: 'POST',
      headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({user1: {username: this.props.currentUser.username},
        user2: {username: this.refs.chatInput.value}})
    }).then(() => this.props.loadData())
    .then(() => this.refs.chatInput.value = '');

  render () {
    const {loadMessages, chat, chats, loadData, currentUser} = this.props

    return (
      <Fragment key={Messages.id}>
        <main className="container chats__view">
          <section className="chats__list">
            <div className="chats__search">
              <input id="chatInput" ref="chatInput"
                type="text" className="chats__input" placeholder="Search"/>
              <img onClick={() => this.addChat()} src={search} alt="search" className="chats__button"/>
            </div>
            <div className="chats__container">
              <ChatsContainer loadChat={loadMessages}
                chats={chats} username={currentUser.username}/>
            </div>      
          </section>

          <section className="messages-container">
            <Switch>
              <Route path='/messages/:username' render={props =>
                <Chat user1={currentUser.username} user2={props.match.params.username}
                  loadChat={loadMessages} chat={chat} {...props}
                  loadData={loadData} currentUser={currentUser}/>}
              />
            </Switch>
          </section>
        </main>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  chats: state.chats,
  chat: state.chat

});

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadChats(username)),
  loadMessages: (username1, username2) => dispatch(loadChat(username1, username2))
});

export default connect(mapStateToProps, mapDispatchToProps)(Messages)