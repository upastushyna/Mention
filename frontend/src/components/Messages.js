import React, { Fragment } from 'react'
import Navigation from "./Navigation";
import ChatContainer from '../containers/ChatContainer'
import {loadChats} from "../actions/chatActions";
import {connect} from 'react-redux'

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
        <Navigation/>
        <div className="container">
          <ChatContainer chats={this.props.chats} username={username}/>
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