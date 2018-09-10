import React, { Fragment } from 'react'
import {Link} from 'react-router-dom'

export default class NotFound extends React.Component {
  render () {
    return (
        <Fragment key={NotFound.id}>
          <section className="error">
           <h1 className="error__type">404</h1>
              <p className="error__description">Oops, the page you are trying to reach does not exist.</p>
              <Link to="/" className="btn-action">
              Return to main page
              </Link>          
          </section>
        </Fragment>
    )
  }
}