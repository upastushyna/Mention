import React from 'react'
import Navigation from './Navigation'
import EditProfile from "../components/EditProfile";

export default class Profile extends React.Component {
  render () {
    return (
      <div key={Profile.id}>
        <div className='container'>
        </div>
        <EditProfile/>
      </div>
    )
  }
}