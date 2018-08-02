import React, { Fragment } from 'react'
import Navigation from "./Navigation";

export default class Body extends React.Component {
  addPost = () => fetch('/api/post',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({body:document.getElementById("input").value, author:{id:1}})
    })

  render () {
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          <h1>OUR FINAL PROJECT</h1>
          <h2>You'll see, everything will be fine.</h2>
          <input id="input" type="text"/><button onClick={() => this.addPost()}>mention</button>
        </div>
      </Fragment>
    )
  }
}