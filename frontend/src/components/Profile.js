import React from 'react'
import Navigation from './Navigation'
import HeaderProfile from '../containers/HeaderProfile'
import EditProfile from "../components/EditProfile";

export default class Profile extends React.Component {
  render () {
    return (
      <div>
        <Navigation/>
        <div className='container'>
        </div>
        <EditProfile/>
      </div>
    )
  }
}