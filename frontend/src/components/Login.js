import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import '../css/index.css'
import logo from '../img/user-icon.png'

export default class Login extends React.Component {
    render () {
        return (
            <Fragment>
                <section className="login d-flex">
                    <div className="login__container">
                        <div className="login__container--icon">
                            <img className="login__container--img" src={logo} alt=""/>
                        </div>
                        <h1 className="login__container--title">Member Login</h1>
                        <div className="login__form">
                            <input type="text" className="login__form--name" placeholder="Username"/>
                            <input type="password" className="login__form--password" placeholder="Password"/>
                            <input type="submit" className="login__form--btn" value="login"/>
                        </div>
                        <Link to="/" className="login__container--forgot">Forgot Password?</Link>
                    </div>
                </section>
            </Fragment>
        )
    }
}