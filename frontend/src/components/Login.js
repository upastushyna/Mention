import React from 'react'
import { Link } from 'react-router-dom'
import '../css/index.css'
import logo from '../img/login.png'

export default class Login extends React.Component {

  login = event => {
    event.preventDefault();
    fetch('/api/login',
      {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({usernameOrEmail: this.refs.username.value,
          password : this.refs.password.value})
      }).then(res => res.json())
      .then(res => localStorage.setItem("accessToken", res.accessToken))
      .then(() => setTimeout(() => this.props.history.push("/"), 500))
  };

  render () {
    return (
      <section className="login d-flex items-center">
        <div className="login__container">
          <div className="login__icon">
            <img className="login__img" src={logo} alt=""/>
          </div>
          <h1 className="login__title">Login</h1>
          <form className="login__form" onSubmit={event => this.login(event)}>
            <input ref="username" type="text" className="login__input" placeholder="Username or Email"/>
            <input ref="password" type="password" className="login__input" placeholder="Password"/>
            <input type="submit" className="login__btn" value="login"/>
          </form>
          <Link to="/" className="login__forgot-password">Forgot Password?</Link>
        </div>
      </section>
    )
  }
}
