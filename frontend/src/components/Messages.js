import React, { Fragment } from 'react'
import {Route, Switch} from 'react-router-dom'
import Navigation from './Navigation'
import {loadChat} from '../actions/singleChatActions'
import ChatsContainer from '../containers/ChatsContainer'
import {loadChats} from '../actions/chatsActions'
import {connect} from 'react-redux'
import Chat from '../containers/Chat'
import search from '../img/search-icon.svg'

class Messages extends React.Component {
  scrollToBottom = () => {
    if (document.getElementById('scroller')) {
      document.getElementById('scroller').scrollIntoView()
    }
  };

  componentWillMount () {
    if (this.props.chats.length === 0) {
      this.props.loadData(this.props.currentUser.username)
    }
  }

  componentDidMount () {
    this.scrollToBottom()
  }

  componentDidUpdate () {
    this.scrollToBottom()
  }

  openChat = () => {
    document.getElementsByClassName('messages-container')[0].style.display = "block";
    document.getElementsByClassName('chats__list')[0].style.display = "none";
    document.getElementsByClassName('messages-container')[0].style.width = "100%";
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
    }).then(() => this.props.loadData(this.props.currentUser.username))
    .then(() => this.refs.chatInput.value = '');

  render () {

    return (
      <Fragment key={Messages.id}>
        <Navigation/>
        <div className="chats__header" onClick={() => this.openChat()}>Go back</div>
        <main className="container chats__view">
          <section className="chats__list">
            <div className="chats__search">
              <input id="chatInput" ref="chatInput"
                type="text" className="chats__input" placeholder="Search"/>
              <img onClick={() => this.addChat()} src={search} alt="search" className="chats__button"/>
            </div>
            <div className="chats__container">
              <ChatsContainer loadChat={this.props.loadMessages}
                chats={this.props.chats} username={this.props.currentUser.username}/>
            </div>      
          </section>

          <section className="messages-container">
            <Switch>
              <Route path='/messages/:username' component={props =>
                <Chat user1={this.props.currentUser.username} user2={props.match.params.username}
                  loadChat={this.props.loadMessages} chat={this.props.chat}
                  loadData={this.props.loadData} currentUser={this.props.currentUser}/>}/>
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

})

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadChats(username)),
  loadMessages: (username1, username2) => dispatch(loadChat(username1, username2))
})

export default connect(mapStateToProps, mapDispatchToProps)(Messages)