import React, { Fragment } from 'react'
import {Route, Switch} from 'react-router-dom'
import Navigation from "./Navigation";
import {loadChat} from "../actions/singleChatActions";
import ChatsContainer from '../containers/ChatsContainer'
import {loadChats} from "../actions/chatsActions";
import {connect} from 'react-redux'
import Chat from "../containers/Chat";
import search from '../img/search-icon.png'

class Messages extends React.Component {

  scrollToBottom = () => {
    if (document.getElementById('scroller')) {
    document.getElementById('scroller').scrollIntoView();
    }
  };

  componentWillMount(){
    if(this.props.chats.length === 0) {
      this.props.loadData(this.props.currentUser.username);
    }
  }

  componentDidMount() {
    this.scrollToBottom();
  }

  componentDidUpdate() {
    this.scrollToBottom();
  }

  addChat = () => fetch('/api/chats/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({user1:{username:this.props.currentUser.username},
        user2:{username:this.refs.chatInput.value}})
    }).then(() => this.props.loadData(this.props.currentUser.username))
    .then(() => this.refs.chatInput.value = "");

  render () {

    return (
      <Fragment>
        <Navigation/>
        <div className="container d-flex">
          <div className="chats">
            <div className="chats__header white-background">
              <h3 className="chats__title">Conversations</h3>
            </div>
            <div className="chats__search d-flex items-center content-between white-background">
              <input id="chatInput" ref="chatInput"
                     type="text" className="chats__input" placeholder="Search"/>
              <img onClick={() => this.addChat()} src={search} alt="" className="chats__button"/>
            </div>
            <div className="chats__list white-background">
              <ChatsContainer loadChat={this.props.loadMessages}
                chats={this.props.chats} username={this.props.currentUser.username}/>
            </div>
          </div>
          <div className="messages-container">
            <Switch>
              <Route path='/messages/:username' component={props =>
                  <Chat user1={this.props.currentUser.username} user2={props.match.params.username}
                        loadChat={this.props.loadMessages} chat={this.props.chat}
                        loadData={this.props.loadData}/>}/>
            </Switch>
          </div>
        </div>
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

export default connect(mapStateToProps, mapDispatchToProps)(Messages);