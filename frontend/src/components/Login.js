import React from 'react'
import { Link } from 'react-router-dom'
import '../css/index.css'
import logo from '../img/posts-icon.png'

export default class Login extends React.Component {
  render () {
    return (
      <section className="login d-flex-center">
        <div className="login__container">
       
            <img className="login__icon" src={logo} alt="login"/>
         
          <h1 className="login__title">Login</h1>
          <form action="#">
            <input type="text" className="login__input" placeholder="Username"/>
            <input type="password" className="login__input" placeholder="Password"/>
            <input type="submit" className="login__btn" value="Login"/>
          </form>
          <Link to="/" className="login__forgot-password">Forgot Password?</Link>
        </div>
      </section>
    )
  }
}
