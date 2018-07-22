/* eslint-disable indent */
import React, { Component, Fragment } from 'react'
import './App.css'
import { Route, Switch } from 'react-router-dom'
import Navigation from './components/Navigation'
import Profile from './components/Profile'
import Messages from './components/Messages'
import CreatePost from './components/CreatePost'
import Favorites from './components/Favorites'
import Body from './components/Body'

class App extends Component {
  render() {
    return (
      <Fragment>
        <Route path='/' component={Navigation}/>
        <Switch>
          <Route path='/messages' component={Messages}/>
          <Route path='/profile' component={Profile}/>
          <Route path='/createPost' component={CreatePost}/>
          <Route path='/favorites' component={Favorites}/>
        </Switch>
          <Body/>
      </Fragment>
    )
  }
}

export default App