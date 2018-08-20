import React from 'react'
import { Link } from 'react-router-dom'
import '../css/index.css'
import logo from '../img/login.png'

export default class Login extends React.Component {
  render () {
    return (
      <section className="login d-flex items-center">
        <div className="login__container">
          <div className="login__icon">
            <img className="login__img" src={logo} alt=""/>
          </div>
          <h1 className="login__title">Login</h1>
          <form className="login__form">
            <input type="text" className="login__input" placeholder="Username"/>
            <input type="password" className="login__input" placeholder="Password"/>
            <input type="submit" className="login__btn" value="login"/>
          </form>
          <Link to="/" className="login__forgot-password">Forgot Password?</Link>
        </div>
      </section>
    )
  }
}
