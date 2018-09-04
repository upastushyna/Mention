/* eslint-disable indent */
import React, { Component, Fragment } from 'react'
import './css/index.css'
import { Route, Switch } from 'react-router-dom'
import Profile from './components/Profile'
import Messages from './components/Messages'
import Favorites from './components/Favorites'
import HomePage from './components/HomePage'
import NotFound from './components/NotFound'
import Registration from './components/Registration'
import UserPage from './components/UserPage'
import EditProfile from "./components/EditProfile";
import SearchPage from './components/SearchPage'
import {connect} from 'react-redux'
import {loadCurrentUser} from './actions/currentUserActions'
import withRouter from 'react-router-dom/es/withRouter'
import Websocket from './components/Websocket'
import {isLoggedIn} from './js/isLoggedIn'
import {loadChats} from "./actions/chatsActions";
import {loadChat} from "./actions/singleChatActions";
import {webSocketConnection2} from "./js/wsConnection";

class App extends Component {
  componentWillMount () {
      if (!this.props.currentUser || !this.props.currentUser.username) {
        if (isLoggedIn()) {
          this.props.loadCurrentUser()
        }else {
          this.props.history.push("/registration")
        }
      }
    webSocketConnection2(this.props.loadMessages, this.props.currentUser.username);
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

  componentDidMount () {
    window.addEventListener('scroll', this.handleOnScroll);
  }

  render () {
    
    return (
      <Fragment>
        <div ref="pageTop" style={{ float: 'left', clear: 'both' }}></div>
        <Switch>
          <Route exact path='/' component={() => <HomePage
            currentUser={this.props.currentUser}
            loadCurrentUser={this.props.loadCurrentUser}
            history={this.props.history}/>}/>
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
          <Route path='/favorites' component={Favorites}/>
          <Route exact path='/websocket' component={Websocket}/>
          <Route path='/search/:input' component={SearchPage}/>
          <Route path='/:username' component={UserPage}/>
          <Route path="*" component={NotFound}/>

        </Switch>
        <button onClick={() => this.scrollToTop()} ref="scroller" className="scroll-btn d-none">&#11014;</button>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  currentUser: state.currentUser
});

const mapDispatchToProps = dispatch => ({
  loadCurrentUser: () => dispatch(loadCurrentUser()),
  loadData: username => dispatch(loadChats(username)),
  loadMessages: (username1, username2) => dispatch(loadChat(username1, username2))
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App))