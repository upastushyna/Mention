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
          <div className="container">
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" defaultValue={this.props.editProfile.firstName}
                     placeholder="First Name"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" defaultValue={this.props.editProfile.secondName}
                     placeholder="Second Name"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" defaultValue={this.props.editProfile.address}
                     placeholder="Address"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" defaultValue={this.props.editProfile.birthDate}
                     placeholder="BirthDay"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" defaultValue={this.props.editProfile.avatarUrl}
                     placeholder="Avatar"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" defaultValue={this.props.editProfile.backgroundUrl}
                     placeholder="Background"/>
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