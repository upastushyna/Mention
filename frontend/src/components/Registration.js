import React from 'react'
import { Link } from 'react-router-dom'
import '../css/index.css'
import logo from '../img/register-icon.png'

export default class Registration extends React.Component {
    render () {
        return (
            <section className="registration d-flex">
                <div className="registration__container">
                    <div className="registration__container--icon">
                        <img className="registration__container--img" src={logo} alt=""/>
                    </div>
                    <h1 className="registration__container--title">Register</h1>
                    <form className="registration__form">
                        <input type="text" className="registration__input" placeholder="Username"/>
                        <input type="text" className="registration__input" placeholder="Email"/>
                        <input type="password" className="registration__input" placeholder="Password"/>
                        <input type="submit" className="registration__btn" value="register"/>
                    </form>
                    <Link to="/" className="registration__container--member">Member Login</Link>
                </div>
            </section>
        )
    }
}