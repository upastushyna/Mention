import React, { Fragment } from 'react'
import {Link} from 'react-router-dom'
export default class NotFound extends React.Component {
    render () {
        return (
            <Fragment key={NotFound.id}>
                <section className="notFound">
                    <div className="notFound__errorText">
                        <h1 className="notFound__errorHeader">404</h1>
                        <p className="notFound__errorBody">Oops, the page you are trying to reach does not exist.</p>
                    </div>
                    <div>
                        <Link to="/"><button className="returnButton">Back to main</button></Link>
                    </div>
                </section>
            </Fragment>
        )
    }
}