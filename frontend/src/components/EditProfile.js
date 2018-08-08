import React, {Fragment} from 'react'
import Navigation from "./Navigation";
import {loadProfile} from "../actions/editProfileAction";
import {connect} from 'react-redux'

class EditProfile extends React.Component {

  componentWillMount() {
    this.props.loadProfileId(49);
  }

  render() {
    return (
        <Fragment>
          <Navigation/>
          <div className="container">
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="firstname"/>
              {this.props.editProfile.firstName}
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="secondname"/>
              {this.props.editProfile.secondName}
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="address"/>
              {this.props.editProfile.address}
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="birthdate"/>
              {this.props.editProfile.birthDate}
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="avatar"/>
              {this.props.editProfile.avatarUrl}
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="background"/>
              {this.props.editProfile.backgroundUrl}
            </p>
            <p className="edit-profile-list">
              <input type="submit" className="edit-profile_button" value="Edit"/>
            </p>
          </div>
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