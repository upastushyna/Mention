import React, { Fragment } from 'react'
import Navigation from "./Navigation";
export default class Messages extends React.Component {
  render () {
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          <p>There will be page with list of user's messages</p>
        </div>
      </Fragment>
    )
  }
}