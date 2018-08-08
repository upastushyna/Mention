import React, { Fragment } from 'react'
import {Route, Switch} from 'react-router-dom'
import Navigation from "./Navigation";
import ChatsContainer from '../containers/ChatsContainer'
import {loadChats} from "../actions/chatsActions";
import {connect} from 'react-redux'
import ChatItem from "./Chat";

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
        <div className="container">
          <div className="chats">
            <div className="chats__header">
              <h3 className="chats__title">Conversations</h3>
            </div>
            <div className="chats__list">
              <Switch>
                <Route exact path={this.props.match.path}
                       render={() => <ChatsContainer chats={this.props.chats} username={username}/>}/>
                <Route path='/messages/:username' render={props =>
                    <ChatItem user1={username} user2={props.match.params.username}/>}/>
              </Switch>
            </div>
          </div>
        </div>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  chats: state.chats

});

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadChats(username))
});

export default connect(mapStateToProps, mapDispatchToProps)(Messages);