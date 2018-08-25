
import React from 'react'
import Feed from './Feed'

export default class HomePage extends React.Component {
  render () {
    return (
      <Feed currentUser={this.props.currentUser}
        loadCurrentUser={this.props.loadCurrentUser}
            history={this.props.history}/>
    )
  }
}