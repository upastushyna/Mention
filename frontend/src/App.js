/* eslint-disable indent */
import React, { Component, Fragment } from 'react'
import './css/index.css'
import { Route, Switch } from 'react-router-dom'
import Profile from './components/Profile'
import Messages from './components/Messages'
import Favorites from './components/Favorites'
import HomePage from './components/HomePage'
import NotFound from './components/NotFound'
import Login from './components/Login'
import Registration from './components/Registration'
import UserPage from './components/UserPage'
import SearchPage from "./components/SearchPage";
import {connect} from "react-redux";
import {loadCurrentUser} from "./actions/currentUserActions";
import {USERNAME} from "./constants/hardcode";

class App extends Component {

  componentWillMount(){
    if(!this.props.currentUser || !this.props.currentUser.username) {
      this.props.loadCurrentUser();
    }
  }

  render() {
    return (
      <Fragment>
        <Switch>
          <Route exact path='/' component={() => <HomePage
            currentUser={this.props.currentUser}
            loadCurrentUser={this.props.loadCurrentUser}/>}/>
          <Route path='/messages' component={() => <Messages
            currentUser={this.props.currentUser}
            loadCurrentUser={this.props.loadCurrentUser}/>}/>
          <Route path="/login" component={Login}/>
          <Route path="/registration" component={Registration}/>
          <Route path='/profile' component={() => <Profile
            currentUser={this.props.currentUser}
            loadCurrentUser={this.props.loadCurrentUser}/>}/>
          <Route path='/favorites' component={Favorites}/>
          <Route path='/search/:input' component={SearchPage}/>
          <Route path='/:username' component={() => <UserPage
            currentUser={this.props.currentUser}
            loadCurrentUser={this.props.loadCurrentUser}/>}/>
          <Route path="*" component={NotFound}/>

        </Switch>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  currentUser: state.currentUser
});

const mapDispatchToProps = dispatch => ({
  loadCurrentUser: () => dispatch(loadCurrentUser()),
});

export default connect(mapStateToProps, mapDispatchToProps)(App);