import React, { Fragment } from 'react'
import {Route, Switch} from 'react-router-dom'
import Navigation from "./Navigation";
import {loadChat} from "../actions/singleChatActions";
import ChatsContainer from '../containers/ChatsContainer'
import {loadChats} from "../actions/chatsActions";
import {connect} from 'react-redux'
import Chat from "../containers/Chat";


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

        <div className="container">
          <div className="chats">
            <div className="chats__header">
              <h3 className="chats__title">Conversations</h3>
            </div>
            <div className="chats__list">
              <ChatsContainer loadChat={this.props.loadMessages}
                chats={this.props.chats} username={username}/>
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