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
      notification: undefined,
      timerId: undefined
    };
  }

  timeout () {
    this.setState({timerId: setTimeout(() =>
        this.triggerHide(), 5000)})
  }

  componentDidMount () {
    webSocketPopUpNotification(this.notify,
      this.props.loadUnread,
      this.props.checkRead,
      this.props.history);
  }

  componentWillReceiveProps() {
    const {timerId} = this.state;

    timerId && clearTimeout(timerId);
    this.timeout();
  }

  triggerHide = () => {
    this.setState({notification:undefined})
  };

  notify = notification => {
    this.setState({notification:notification})
  };

  render () {
    const {unread, currentUser, checkRead, loadUnread, history} = this.props;
    const {notification} = this.state;

    if (!this.props.currentUser || !this.props.currentUser.username) {
      return ' ';
    }

    return (
      <Fragment key={Navigation.id}>
        <HeaderPanel unread={unread.length} history={history}
                     currentUser={currentUser}/>
        <PopUpNotification  notification={notification}
                           checkRead={checkRead}
                           loadUnread={loadUnread}/>
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