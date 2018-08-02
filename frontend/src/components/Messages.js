import React, { Fragment } from 'react'
import Navigation from "./Navigation";
export default class Messages extends React.Component {
  render () {
    return (
      <Fragment>
        <Navigation/>
        <p>There will be page with list of user's messages</p>
      </Fragment>
    )
  }
}