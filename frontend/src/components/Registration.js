import React from 'react'
import { Link } from 'react-router-dom'
import '../css/index.css'
import logo from '../img/register.png'

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
      <section className="registration d-flex items-center">
        <div className="registration__container">
          <div className="registration__icon">
            <img className="registration__img" src={logo} alt=""/>
          </div>
          <h1 className="registration__title">Register</h1>
          <div className="registration__form">
            <input id="username" type="text" className="registration__input" placeholder="Username"/>
            <input id="email" type="text" className="registration__input" placeholder="Email"/>
            <input id="password" type="password" className="registration__input" placeholder="Password"/>
            <button className="registration__btn" onClick={() => this.addUser()}>Register</button>
          </div>
          <Link to="/" className="registration__member-login">Member Login</Link>
        </div>
      </section>
    )
  }
}