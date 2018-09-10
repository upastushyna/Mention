import React, { Fragment } from 'react'
import HeaderPanel from '../containers/HeaderPanel'
import withRouter from 'react-router-dom/es/withRouter'
import {connect} from 'react-redux'
import {loadCurrentUser} from '../actions/currentUserActions'
import {webSocketMessageNotification} from "../js/wsConnection";
import PopUpNotification from "../containers/PopUpNotification";

class Navigation extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      notification: undefined
    };
  }

  componentWillMount () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      this.props.loadCurrentUser()
    }
  }

  componentDidMount () {
    webSocketMessageNotification(this.notify);
  }

  notify = notification => {
    this.setState({notification:notification})
  };

  render () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      return 'Loading...'
    }

    return (
      <Fragment key={Navigation.id}>
        <HeaderPanel history={this.props.history} currentUser={this.props.currentUser}/>
        <PopUpNotification notification={this.state.notification}/>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  currentUser: state.currentUser
})

const mapDispatchToProps = dispatch => ({
  loadCurrentUser: () => dispatch(loadCurrentUser())
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Navigation))