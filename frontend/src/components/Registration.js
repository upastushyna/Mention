import React, {Fragment}  from 'react'
import {Route, Switch, Link} from 'react-router-dom'
import RegisterConfirmation from '../containers/RegisterConfirmation'
import '../css/index.css'
import logo from '../img/posts-icon.png'

export default class Registration extends React.Component {

  login = event => {
    event.preventDefault();
    fetch('/api/login',
      {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({usernameOrEmail: this.refs.loginUsername.value,
          password : this.refs.loginPassword.value})
      }).then(res => res.json())
      .then(res => res.accessToken? localStorage.setItem('accessToken', res.accessToken) : null)
      .then(() => localStorage.getItem("accessToken")?
        setTimeout(() => this.props.history.push("/"), 1000) : console.log(localStorage.getItem("accessToken")))
  };

  register = () => {
    fetch('/api/register',
      {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({username: this.refs.registerUsername.value,
          email: this.refs.registerEmail.value,
          password : this.refs.registerPassword.value})
      }).then(res => res.json())
      .then(res => console.log(res.status))
  };

  showRegister = () => {
    this.refs.registerForm.classList.remove("d-none");
    this.refs.loginForm.classList.add("d-none");
  };

  showLogin = () => {
    this.refs.registerForm.classList.add("d-none");
    this.refs.loginForm.classList.remove("d-none");
  };

  render () {
    return (

      <Fragment>
        <section ref="registerForm" className="login d-flex-center d-none">
          <div className="login__container">
              <img className="login__icon" src={logo} alt="profile"/>
            <h1 className="login__title">Join the community</h1>
            <div className="login__form">
              <input ref="registerUsername" type="text" className="login__input" placeholder="Username"/>
              <input ref="registerEmail" type="text" className="login__input" placeholder="Email"/>
              <input ref="registerPassword" type="password" className="login__input" placeholder="Password"/>
              <button className="login__btn" onClick={() => this.register()}>Register</button>
            </div>
            <button onClick={() => this.showLogin()} className="login__forgot-password">Member Login</button>
          </div>
        </section>
        <section ref="loginForm" className="login d-flex-center">
          <div className="login__container">

            <img className="login__icon" src={logo} alt="login"/>

            <h1 className="login__title">Login</h1>
            <form className="login__form" onSubmit={event => this.login(event)}>
              <input ref="loginUsername" type="text" className="login__input" placeholder="Username or Email"/>
              <input ref="loginPassword" type="password" className="login__input" placeholder="Password"/>
              <input type="submit" className="login__btn" value="login"/>
            </form>
            <button onClick={() => this.showRegister()} className="login__forgot-password">Sign up</button>
            <Link to="/" className="login__forgot-password">Forgot Password?</Link>
          </div>
        </section>
        <Switch>
          <Route path='/registration/:userToken' component={props =>
            <RegisterConfirmation userToken={props.match.params.userToken}/>}/>
        </Switch>
      </Fragment>

    )
  }
}