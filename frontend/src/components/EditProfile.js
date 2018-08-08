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
              {this.props.editProfile.firstname}
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="secondname"/>
              {this.props.editProfile.secondname}
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="address"/>
              {this.props.editProfile.address}
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="birthdate"/>
              {this.props.editProfile.birthdate}
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="avatar"/>
              {this.props.editProfile.avatar}
            </p>
            <p className="edit-profile-list">
              <input type="text" className="edit-profile_input" placeholder="background"/>
              {this.props.editProfile.background}
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