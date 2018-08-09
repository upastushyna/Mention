import React, { Fragment } from 'react'
import {Route, Switch} from 'react-router-dom'
import Navigation from "./Navigation";
import {loadChat} from "../actions/singleChatActions";
import ChatsContainer from '../containers/ChatsContainer'
import {loadChats} from "../actions/chatsActions";
import {connect} from 'react-redux'
import Chat from "../containers/Chat";
import avatar from '../img/header-panel/user-img.png'

const username = "yarik";

class Messages extends React.Component {

  componentWillMount(){
    if(this.props.chats.length === 0) {
      this.props.loadData(username);
    }
  }

  render () {
    return (
      <Fragment>
        <Navigation/>
        <div className="container d-flex">
          <div className="chats">
            <div className="chats__header">
              <h3 className="chats__title">Conversations</h3>
            </div>
            <div className="chats__list">
              <ChatsContainer loadChat={this.props.loadMessages}
                chats={this.props.chats} username={username}/>
            </div>
          </div>
          <div className="messages-container">
            <div className="messages-container__header">
              <div className="profile-small d-flex">
                <img src={avatar} alt="" className="profile-small__avatar"/>
                <div className="profile-small__signature">
                  <h2 className="profile-small__username color-dark-grey">Admin</h2>
                  <span className="profile-small__alias">Online</span>
                </div>
              </div>
            </div>
            <div className="messages-container__body">
              <Switch>
                <Route path='/messages/:username' component={props =>
                  <Chat user1={username} user2={props.match.params.username}
                        loadChat={this.props.loadMessages} chat={this.props.chat}/>}/>
              </Switch>
            </div>
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