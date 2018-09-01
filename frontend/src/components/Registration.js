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
      .then(res => res.accessToken? localStorage.setItem('accessToken', res.accessToken) : this.showMessage(res))
      .then(() => localStorage.getItem("accessToken")?
        setTimeout(() => this.props.history.push("/"), 1000) : null)
  };

  showMessage = res => {
    if (res.status) {
      this.refs.passwordError.classList.remove('d-none');
      this.refs.passwordError.innerText = 'Wrong password';
      console.log(res)
    }else {
      this.refs.loginError.classList.remove('d-none');
      this.refs.loginError.innerText = res.message;
      console.log(res)
    }
  };

  register = event => {
    event.preventDefault();
    if (this.refs.registerPassword.value !== this.refs.confirmPassword.value) {
      this.refs.confirmPasswordRegister.innerText = "Passwords must match";
      this.refs.confirmPasswordRegister.classList.remove('d-none');
      return;
    }
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
      .then(res => res.status === 200? this.showSuccess(res) : this.showError(res))
  };

  showSuccess = res => {
    this.refs.successMs.innerText = res.message;
    this.refs.successMs.classList.remove('d-none');
  };

  showError = res => {
    if (res.message.indexOf("Username")) {
      this.refs.usernameRegister.innerText = res.message;
      this.refs.usernameRegister.classList.remove('d-none');
    } else if(res.message.indexOf("Email")) {
      this.refs.emailRegister.innerText = res.message;
      this.refs.emailRegister.classList.remove('d-none');
    }
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
            <form onSubmit={event => this.register(event)}>
              <div className="login__form">
                <input ref="registerUsername" type="text" className="input_custom" placeholder="Username" minLength="3" maxLength="20"/>
                <p ref="usernameRegister" className="d-none"></p>
                <input ref="registerEmail" type="text" className="input_custom" placeholder="Email"/>
                <p ref="emailRegister" className="d-none"></p>
                <input ref="registerPassword" type="password" className="input_custom" placeholder="Password" minLength="6" maxLength="24"/>
                <p ref="passwordRegister" className="d-none"></p>
                <input ref="confirmPassword" type="password" className="input_custom"
                       placeholder="Confirm Password"/>
                <p ref="confirmPasswordRegister" className="d-none"></p>
                <input className="btn-action login__btn" type="submit" placeholder="Register"/>
                <p ref="successMs" className="d-none"></p>
              </div>
            </form>
            <p onClick={() => this.showLogin()} className="login__forgot-password">Member Login</p>
          </div>
        </section>
        <section ref="loginForm" className="login d-flex-center">
          <div className="login__container">

            <img className="login__icon" src={logo} alt="login"/>

            <h1 className="login__title">Login</h1>
            <form onSubmit={event => this.login(event)}>
              <input ref="loginUsername" type="text" className="input_custom" placeholder="Username or Email" minLength="3"/>
              <p ref="loginError" className="d-none"></p>
              <input ref="loginPassword" type="password" className="input_custom" placeholder="Password" minLength="6" maxLength="24"/>
              <p ref="passwordError" className="d-none"></p>
              <input type="submit" className="btn-action login__btn" value="login"/>
            </form>
            <button onClick={() => this.showRegister()} className="login__sign-btn">Sign up</button>
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