import React, {Fragment} from 'react'
import {Route, Switch, Link} from 'react-router-dom'
import RegisterConfirmation from '../containers/RegisterConfirmation'
import '../css/index.css'
import logo from '../img/posts-icon.png'

export default class Registration extends React.Component {
  state = {
    /*showRegister: false*/
  };

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
            password: this.refs.loginPassword.value
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
  }

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
  }

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
        .then(res => this.showResponse(res))
  };

  showResponse = res => {
    if (res.message.indexOf('Validation') === 0) {
      this.createMessage('Please, enter a valid email address',
          document.getElementById('registerForm'), 0);
    } else if (res.message.indexOf('confirmation')) {
      this.createValidMessage(res.message, document.getElementById('registerForm'), 0);
    } else {
      this.createMessage(res.message, document.getElementById('registerForm'), 0);
    }
  };

  showRegister = () => {
    this.refs.registerForm.classList.remove('d-none');
    this.refs.loginForm.classList.add('d-none')
  };

  showLogin = () => {
    this.refs.registerForm.classList.add('d-none');
    this.refs.loginForm.classList.remove('d-none')
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
              <form onSubmit={event => this.register(event)}>
                <div id='registerForm' className="login__form">
                  <input ref="registerUsername" type="text" className="input_custom" placeholder="Username"
                         minLength="3" maxLength="20"/>
                  {/*<input ref="registerUsername" type="text" className={`input input_text ${showRegister ? 'input_custom' : 'd-none'}`} placeholder="Username" minLength="3" maxLength="20"/>*/}
                  <input ref="registerEmail" type="text" className="input_custom" placeholder="Email"/>
                  <input ref="registerPassword" type="password" className="input_custom" placeholder="Password"
                         minLength="6" maxLength="24"/>
                  <input ref="confirmPassword" type="password" className="input_custom"
                         placeholder="Confirm Password"/>
                  <input className="btn-action login__btn" type="submit" placeholder="Register"/>
                </div>
              </form>
              <p onClick={() => this.showLogin()} className="login__forgot-password">Member Login</p>
              {/*<p onClick={() => this.setState({showRegister: true})} className="login__forgot-password">Member Login</p>*/}
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