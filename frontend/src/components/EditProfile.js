import React, {Fragment} from 'react'
import Navigation from "./Navigation";
import EditUserProfile from "../containers/EditUserProfile"
import {loadProfile} from "../actions/editProfileAction";
import {connect} from 'react-redux'

class EditProfile extends React.Component {

  componentWillMount() {
    if (this.props.editProfile.length === 0) {
      this.props.loadProfileId(49);
    }
  }

  render() {
    const edit = this.props.editProfile.map(editor =>
        <EditProfile editor={editor}/>)
    return (
        <Fragment>
          <Navigation/>
          <EditUserProfile/>
        </Fragment>
    )
  }

}

const mapStateToProps = state => ({
  editProfile: state.editProfile
});

const mapDispatchToProps = dispatch => ({
  loadProfileId: id => dispatch(loadProfile(id))
});

export default connect(mapStateToProps, mapDispatchToProps)(EditProfile)