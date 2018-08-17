import React, { Fragment } from 'react'
import HeaderPanel from '../containers/HeaderPanel'
import LeftPanel from '../containers/LeftPanel'
import withRouter from "react-router-dom/es/withRouter";
import {connect} from "react-redux";
import {loadCurrentUser} from "../actions/currentUserActions";

class Navigation extends React.Component {
  componentWillMount() {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      this.props.loadCurrentUser();
    }
  }
  render () {
    if (!this.props.currentUser || !this.props.currentUser.username) {
      return "Loading..."
    }

    return (
      <Fragment>
        <HeaderPanel currentUser={this.props.currentUser}/>
        <LeftPanel/>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  currentUser: state.currentUser
});

const mapDispatchToProps = dispatch => ({
  loadCurrentUser: () => dispatch(loadCurrentUser())
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Navigation));