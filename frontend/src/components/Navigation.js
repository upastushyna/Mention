import React, { Fragment } from 'react'
import HeaderPanel from '../containers/HeaderPanel'
import withRouter from 'react-router-dom/es/withRouter'
import {connect} from 'react-redux'
import {loadCurrentUser} from '../actions/currentUserActions'
import {webSocketPopUpNotification} from "../js/wsConnection";
import PopUpNotification from "../containers/PopUpNotification";
import {checkReadNotification} from "../actions/notificationsActions";

class Navigation extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      notification: undefined
    };
  }

  componentDidMount () {
    webSocketPopUpNotification(this.notify,
      this.props.loadUnread,
      this.props.checkRead,
      this.props.history);
  }

  notify = notification => {
    this.setState({notification:notification})
  };

  render () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      return ' ';
    }

    return (
      <Fragment key={Navigation.id}>
        <HeaderPanel unread={this.props.unread.length} history={this.props.history}
                     currentUser={this.props.currentUser}/>
        <PopUpNotification notification={this.state.notification}
                           checkRead={this.props.checkRead}
                           loadUnread={this.props.loadUnread}/>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  currentUser: state.currentUser,
  unread:state.unread
});

const mapDispatchToProps = dispatch => ({
  loadCurrentUser: () => dispatch(loadCurrentUser()),
  checkRead: (callback, id) => checkReadNotification(callback, id)
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Navigation))