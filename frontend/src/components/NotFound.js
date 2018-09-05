import React, { Fragment } from 'react'
import {Link} from 'react-router-dom'
import back from '../img/back-icon.png'

export default class NotFound extends React.Component {
  render () {
    return (
        <Fragment key={NotFound.id}>
          <section className="notFound">
            <div className="notFound__errorText">
              <p className="notFound__errorBody">Oops, the page you are trying to reach does not exist.</p>
              <Link to="/">
                <img src={back} alt="" className="returnButton"/>
              </Link>
            </div>
          </section>
        </Fragment>
    )
  }
}