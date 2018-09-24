import React, {Fragment} from 'react'
import {Route, Switch, Link} from 'react-router-dom'
import RegisterConfirmation from '../containers/RegisterConfirmation'
import RecoverPassword from '../containers/RecoverPassword'
import '../css/index.css'
import logo from '../img/posts-icon.png'

export default class Registration extends React.Component {

  setToken = token => {
    localStorage.setItem('recoverToken', token)
  };

  componentDidMount() {
    if (localStorage.getItem('recoverToken')) {
      this.showRecover();
    }
  }

  login = event => {
    event.preventDefault();
    fetch('/api/login',
        {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            usernameOrEmail: this.refs.loginUsername.value,
            password: this.refs.loginPassword.value,
            isRemembered: this.refs.rememberMe.checked
          })
        }).then(res => res.json())
        .then(res => res.accessToken ? localStorage.setItem('accessToken', res.accessToken) : this.showMessage(res))
        .then(() => localStorage.getItem('accessToken')
            ? setTimeout(() => this.props.history.push('/'), 1000) : null)
  };

  showMessage = res => {
    if (res.status) {
      this.createMessage('Wrong password', document.getElementById('loginForm'), 0);
    } else {
      this.createMessage(res.message, document.getElementById('loginForm'), 0);
    }
  };

  displayMessage = () => {
    this.refs.message.classList.remove('d-none');
    this.refs.message.classList.add('login__error');
  };

  displayValidMessage = () => {
    this.refs.message.classList.remove('d-none');
    this.refs.message.classList.add('login__valid');
  };

  setMessage = message => {
    this.refs.message.innerText = message;
  };

  insertMessage = (el, index) => {
    el.insertBefore(this.refs.message, el.children[index])
  };

  createMessage = (message, el, index) => {
    this.setMessage(message);
    this.displayMessage();
    this.insertMessage(el, index)
  };
  createValidMessage = (message, el, index) => {
    this.setMessage(message);
    this.displayValidMessage();
    this.insertMessage(el, index)
  };

  recoverPassword = event => {
    event.preventDefault();
    fetch('/api/recover',
      {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          email: this.refs.forgotEmail.value
        })
      }).then(res => res.json())
      .then(res => this.showResponse(res, document.getElementById('forgotForm')))
  };

  changePassword = event => {
    event.preventDefault();
    if (this.refs.restorePassword.value !== this.refs.confirmRestore.value) {
      this.createMessage('Passwords must match',
        document.getElementById('restoreForm'), 0)
    }
    fetch('/api/recover/' + localStorage.getItem('recoverToken'),
      {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          password: this.refs.restorePassword.value
        })
      }).then(res => res.json())
      .then(res => {
        res.success? localStorage.removeItem('recoverToken') : null;
        this.showResponse(res, document.getElementById('restoreForm'));
      })
      .then(() => {
        this.refs.restorePassword.value = '';
        this.refs.confirmRestore.value = '';
      })
  };

  register = event => {
    event.preventDefault();
    if (this.refs.registerPassword.value !== this.refs.confirmPassword.value) {
      this.createMessage('Passwords must match', document.getElementById('registerForm'), 0);
      return
    }
    fetch('/api/register',
        {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            username: this.refs.registerUsername.value,
            email: this.refs.registerEmail.value,
            password: this.refs.registerPassword.value
          })
        }).then(res => res.json())
        .then(res => this.showResponse(res, document.getElementById('registerForm')))
  };

  showResponse = (res, el) => {
    console.log(res.message);
    if (res.message.indexOf('Validation') >= 0) {
      this.createMessage('Please, enter a valid email address',
          el, 0);
    } else if (res.message.indexOf('confirmation') >= 0 ||
      res.message.indexOf('successfully') >= 0) {
      this.createValidMessage(res.message, el, 0);
    } else {
      this.createMessage(res.message, el, 0);
    }
  };

  showRegister = () => {
    this.refs.registerForm.classList.remove('d-none');
    this.refs.loginForm.classList.add('d-none')
  };

  showLogin = () => {
    this.refs.registerForm.classList.add('d-none');
    this.refs.forgotForm.classList.add('d-none');
    this.refs.restoreForm.classList.add('d-none');
    this.refs.loginForm.classList.remove('d-none');
  };

  showForgot = () => {
    this.refs.loginForm.classList.add('d-none');
    this.refs.forgotForm.classList.remove('d-none');
  };

  showRecover = () => {
    if(this.refs.loginForm && this.refs.restoreForm) {
      this.refs.loginForm.classList.add('d-none');
      this.refs.restoreForm.classList.remove('d-none');
    }
  };

  render() {
    // const {showRegister} = this.state

    return (
        <Fragment key={Registration.id}>
          <div>
            <p className='d-none' ref='message'></p>
          </div>
          <section ref="registerForm" className="login d-flex-center d-none">
            <div className="login__container">
              <img className="login__icon" src={logo} alt="profile"/>
              <h1 className="login__title">Join the community</h1>
              <form id='registerForm' className="login__form" onSubmit={event => this.register(event)}>
               
                  <input ref="registerUsername" type="text" className="input_custom" placeholder="Username"
                         minLength="3" maxLength="20"/>
                  {/*<input ref="registerUsername" type="text" className={`input input_text ${showRegister ? 'input_custom' : 'd-none'}`} placeholder="Username" minLength="3" maxLength="20"/>*/}
                  <input ref="registerEmail" type="text" className="input_custom" placeholder="Email"/>
                  <input ref="registerPassword" type="password" className="input_custom" placeholder="Password"
                         minLength="6" maxLength="24"/>
                  <input ref="confirmPassword" type="password" className="input_custom"
                         placeholder="Confirm Password"/>
                  <input className="login__btn" type="submit" placeholder="Register"/>
          
              </form>
              <button onClick={() => this.showLogin()} className="login__sign-btn">Member Login</button>
            </div>
          </section>
          <section ref="loginForm" className="login d-flex-center">
            <div className="login__container">

              <img className="login__icon" src={logo} alt="login"/>

              <h1 className="login__title">Login</h1>
              <form id='loginForm' onSubmit={event => this.login(event)}>
                <input ref="loginUsername" type="text" className="input_custom" placeholder="Username or Email"
                       minLength="3"/>
                <input ref="loginPassword" type="password" className="input_custom" placeholder="Password" minLength="6"
                       maxLength="24"/>
                <input type="submit" className="login__btn" value="login"/>
                <label className="login__remember">
                <input type="checkbox" name="feature" ref='rememberMe'
                       defaultValue="Remember Me" className="default-checkbox"/>
                       <div className="login__checkbox"></div>
                <p>Remember me</p>
                </label>
              </form>
              <button onClick={() => this.showRegister()} className="login__sign-btn">Sign up</button>
              <button onClick={() => this.showForgot()} className="login__forgot-password">Forgot Password?</button>
            </div>
          </section>
          <section ref="forgotForm" className="login d-flex-center d-none">
            <div className="login__container">

              <img className="login__icon" src={logo} alt="login"/>

              <h1 className="login__title">Enter your email address</h1>
              <form id='forgotForm' onSubmit={event => this.recoverPassword(event)}>
                <input ref="forgotEmail" type="text" className="input_custom" placeholder="Email address"
                       minLength="3"/>
                <input type="submit" className="btn-action login__btn" value="Send email"/>
              </form>
              <button onClick={() => this.showLogin()} className="login__sign-btn">Sign in</button>
            </div>
          </section>
          <section ref="restoreForm" className="login d-flex-center d-none">
            <div className="login__container">

              <img className="login__icon" src={logo} alt="login"/>

              <h1 className="login__title">Enter your new password</h1>
              <form id='restoreForm' onSubmit={event => this.changePassword(event)}>
                <input ref="restorePassword" type="password" className="input_custom" placeholder="New Password"
                       minLength="6" maxLength="24"/>
                <input ref="confirmRestore" type="password" className="input_custom"
                       placeholder="Confirm Password"/>
                <input type="submit" className="btn-action login__btn" value="Change password"/>
              </form>
              <button onClick={() => this.showLogin()} className="login__sign-btn">Return to Login</button>
            </div>
          </section>
          <Switch>
            <Route path='/registration/recover/:userToken' component={props =>
              <RecoverPassword userToken={props.match.params.userToken}
                               setToken={this.setToken}/>}/>
            <Route path='/registration/:userToken' component={props =>
                <RegisterConfirmation userToken={props.match.params.userToken}/>}/>
          </Switch>
        </Fragment>

    )
  }
}