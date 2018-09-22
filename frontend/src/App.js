/* eslint-disable indent */
import React, { Component, Fragment } from 'react'
import './css/index.css'
import { Route, Switch } from 'react-router-dom'
import Profile from './components/Profile'
import Messages from './components/Messages'
import NotFound from './components/NotFound'
import Registration from './components/Registration'
import UserPage from './components/UserPage'
import EditProfile from "./components/EditProfile";
import SearchPage from './components/SearchPage'
import {connect} from 'react-redux'
import {loadCurrentUser} from './actions/currentUserActions'
import withRouter from 'react-router-dom/es/withRouter'
import {isLoggedIn} from './js/isLoggedIn'
import Post from "./components/Post";
import {loadFeed} from "./actions/feedActions";
import Navigation from "./components/Navigation"
import Feed from './components/Feed'
import {loadUnreadNotifications} from "./actions/notificationsActions";
import Notifications from "./components/Notifications"


class App extends Component {
  componentWillMount () {
      if (!this.props.currentUser || !this.props.currentUser.username) {
        if (isLoggedIn()) {
          this.props.loadCurrentUser();
          this.props.loadUnread()
        }else {
          this.props.history.push("/registration")
        }
      }
  }

  componentDidMount () {
    window.addEventListener('scroll', this.handleOnScroll);
  }

  handleOnScroll = () => {
    if(window.pageYOffset > 260 && this.refs.scroller) {
      this.refs.scroller.classList.remove("d-none");
    }
    if(window.pageYOffset < 260 && !this.refs.scroller.classList.contains("d-none")) {
      this.refs.scroller.classList.add('d-none');
    }
  };

  scrollToTop = () => {
    this.refs.pageTop.scrollIntoView({ block: 'end',  behavior: 'smooth' });
  };

  render () {
    
    return (
      <Fragment>
        <div ref="pageTop" style={{ float: 'left', clear: 'both' }}></div>
        {window.location.pathname !== '/registration'?
        <Navigation loadUnread={this.props.loadUnread}
                    history={this.props.history}/> : null}
        <Switch>
          <Route exact path='/' component={Feed}/>
          <Route path='/messages' component={() => <Messages
            currentUser={this.props.currentUser}
            loadCurrentUser={this.props.loadCurrentUser}/>}/>
          <Route path="/registration" component={() => <Registration
            history={this.props.history}/>}/>
          <Route path="/registration" component={Registration}/>
          <Route path='/editprofile' component={() => <EditProfile
              currentUser={this.props.currentUser}
              loadCurrentUser={this.props.loadCurrentUser}/>}/>
          <Route path='/profile' component={() => <Profile
            currentUser={this.props.currentUser}
            loadCurrentUser={this.props.loadCurrentUser}/>}/>
          <Route path='/search/:input' component={SearchPage}/>
          <Route path='/user/:username' component={UserPage}/>
          <Route path='/post/:id' component={props => <Post
            {...props}
            currentUser={this.props.currentUser}
            loadCurrentUser={this.props.loadCurrentUser}
            history={this.props.history}/>}/>
          <Route path='/notifications' component={() => <Notifications
                 unread={this.props.unread}
                 loadUnread={this.props.loadUnread}
                 currentUser={this.props.currentUser}/>}/>
          <Route path="*" component={NotFound}/>
        </Switch>
        <button onClick={() => this.scrollToTop()} ref="scroller" className="scroll-btn d-none">&#11014;</button>
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
  loadUnread: () => dispatch(loadUnreadNotifications())
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App))