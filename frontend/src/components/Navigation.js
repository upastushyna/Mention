import React, { Fragment } from 'react'
import HeaderPanel from '../containers/HeaderPanel'
import LeftPanel from '../containers/LeftPanel'

class Navigation extends React.Component {
  render () {
    return (
      <Fragment>
        <HeaderPanel/>
        <LeftPanel/>
      </Fragment>
    )
  }
}

export default Navigation