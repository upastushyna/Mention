import React, { Fragment } from 'react'
import {Route, Switch, Link} from 'react-router-dom'
import NotificationsContainer from "../containers/NotificationsContainer";
import withRouter from "react-router-dom/es/withRouter";
import {connect} from "react-redux";
import {checkReadAll, checkReadNotification, loadAllNotifications} from "../actions/notificationsActions";

class Notifications extends React.Component {

  componentWillMount() {
    this.props.loadNotifications()
  }

  render () {
    return (
      <Fragment>
        <div className="container">
          <div className="info-controller">
              {this.props.unread.length > 0 ?
            <button className="info-controller__link info-controller__link_white"
                    onClick={() => this.props.checkReadAll(this.props.loadUnread)}>
              Mark all as read</button> : null }
            <Link className="info-controller__link" to={'/notifications/unread'}>Unread</Link>
            <Link className="info-controller__link" to={'/notifications/all'}>All</Link>
          </div>
        </div>
       <section className="notify__container">
        <Switch>
          <Route exact path='/notifications/all' component={() => <NotificationsContainer
            notifications={this.props.notifications}
            loadNotifications={this.props.loadNotifications}
            checkRead={this.props.checkRead}/>}/>
          <Route exact path='/notifications/unread' component={() => <NotificationsContainer
              notifications={this.props.unread}
              loadNotifications={this.props.loadUnread}
              checkRead={this.props.checkRead}/>}/>
        </Switch>
        </section>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  notifications:state.notifications
});

const mapDispatchToProps = dispatch => ({
  loadNotifications: () => dispatch(loadAllNotifications()),
  checkReadAll: callback => checkReadAll(callback),
  checkRead: (callback, id) => checkReadNotification(callback, id)
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Notifications))