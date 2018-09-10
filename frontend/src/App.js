/* eslint-disable indent */
import React, { Component, Fragment } from 'react'
import './css/index.css'
import { Route, Switch } from 'react-router-dom'
import Profile from './components/Profile'
import Messages from './components/Messages'
import HomePage from './components/HomePage'
import NotFound from './components/NotFound'
import Registration from './components/Registration'
import UserPage from './components/UserPage'
import EditProfile from "./components/EditProfile";
import SearchPage from './components/SearchPage'
import {connect} from 'react-redux'
import {loadCurrentUser} from './actions/currentUserActions'
import withRouter from 'react-router-dom/es/withRouter'
import {isLoggedIn} from './js/isLoggedIn'
import {loadChats} from "./actions/chatsActions";
import {loadChat} from "./actions/singleChatActions";
import Post from "./components/Post";
import {webSocketFeed} from "./js/wsConnection";
import {loadFeed} from "./actions/feedActions";


class App extends Component {
  componentWillMount () {
      if (!this.props.currentUser || !this.props.currentUser.username) {
        if (isLoggedIn()) {
          this.props.loadCurrentUser()
        }else {
          this.props.history.push("/registration")
        }
      }
  }

  componentDidMount () {
    window.addEventListener('scroll', this.handleOnScroll);
    webSocketFeed(this.props.loadFeed);
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
        <Switch>
          <Route exact path='/' component={() => <HomePage
            currentUser={this.props.currentUser}
            loadCurrentUser={this.props.loadCurrentUser}
            history={this.props.history}
            feed={this.props.feed}/>}/>
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
            loadCurrentUser={this.props.loadCurrentUser}/>}/>
          <Route path="*" component={NotFound}/>
        </Switch>
        <button onClick={() => this.scrollToTop()} ref="scroller" className="scroll-btn d-none">&#11014;</button>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  currentUser: state.currentUser,
  feed:state.feed
});

const mapDispatchToProps = dispatch => ({
  loadCurrentUser: () => dispatch(loadCurrentUser()),
  loadData: username => dispatch(loadChats(username)),
  loadMessages: (username1, username2) => dispatch(loadChat(username1, username2)),
  loadFeed: username => dispatch(loadFeed(username))
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App))