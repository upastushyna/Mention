import React, { Fragment } from 'react'
import {Route, Switch} from 'react-router-dom'
import Navigation from "./Navigation";
import ChatContainer from '../containers/ChatContainer'
import {loadChats} from "../actions/chatsActions";
import {connect} from 'react-redux'
import ChatItem from "../containers/ChatItem";

const username = "yarik"

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
          <Switch>
            <Route exact path={this.props.match.path}
                   render={() => <ChatContainer chats={this.props.chats} username={username}/>}/>
            <Route path='/messages/:username' render={props =>
              <ChatItem user1={username} user2={props.match.params.username}/>}/>
          </Switch>
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