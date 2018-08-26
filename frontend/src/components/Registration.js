import React from 'react'
import { Link } from 'react-router-dom'
import '../css/index.css'
import logo from '../img/posts-icon.png'

export default class Registration extends React.Component {
  addUser = () => fetch('/api/user',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({username: document.getElementById('username').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value})
    })

  render () {
    return (

      
      <section className="login d-flex-center">
        <div className="login__container">     
            <img className="login__icon" src={logo} alt="profile"/>      
          <h1 className="login__title">Join the community</h1>
          <div className="login__form">
            <input id="username" type="text" className="login__input" placeholder="Username"/>
            <input id="email" type="text" className="login__input" placeholder="Email"/>
            <input id="password" type="password" className="login__input" placeholder="Password"/>
            <button className="login__btn" onClick={() => this.addUser()}>Register</button>
          </div>
          <Link to="/" className="login__forgot-password">Member Login</Link>
        </div>
      </section>
    )
  }
}