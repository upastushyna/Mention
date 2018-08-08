import React, {Fragment} from 'react'
import {loadChat} from "../actions/singleChatActions";
import {connect} from "react-redux";

class ChatItem extends React.Component {

  componentWillMount() {
    this.props.loadMessages(this.props.user1, this.props.user2);
  }

  render() {
    console.log(this.props.chat)

    if (!this.props.chat || !this.props.chat.messages) {
      return 'Loading...'
    }


    const messages = this.props.chat.messages.map(message =>
        <p>{message.content}</p>)
    return (
        <Fragment>
          {messages}
        </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  chat: state.chat

});

const mapDispatchToProps = dispatch => ({
  loadMessages: (username1, username2) => dispatch(loadChat(username1, username2))
});

export default connect(mapStateToProps, mapDispatchToProps)(ChatItem);