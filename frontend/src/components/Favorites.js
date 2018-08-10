import React, { Fragment } from 'react'
import Navigation from "./Navigation";
import CreatePost from "../containers/CreatePost";
export default class Favorites extends React.Component {
  render () {
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          <p>There will be Favorites page</p>
          <CreatePost/>
        </div>
      </Fragment>
    )
  }
}