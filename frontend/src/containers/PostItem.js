import React, { Fragment } from 'react'

const PostItem = props =>
  <Fragment>
    <img src={this.props.avatar}/>
    <h2>{this.props.username}</h2>
    <p>{this.props.body}</p>
  </Fragment>