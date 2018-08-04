import React, { Fragment } from 'react'
import Navigation from "./Navigation";
import ProfileHeader from '../containers/ProfileHeader'

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
        <ProfileHeader/>
      </Fragment>
    )
  }
}