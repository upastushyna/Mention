import React, { Fragment } from 'react'
import Navigation from "./Navigation";
export default class Favorites extends React.Component {
  render () {
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          <p>There will be Favorites page</p>
        </div>
      </Fragment>
    )
  }
}