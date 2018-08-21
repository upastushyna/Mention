import React, {Fragment} from 'react'
import {loadProfile} from "../actions/editProfileAction";
import {connect} from 'react-redux'
import Navigation from "./Navigation";

class EditProfile extends React.Component {

  componentWillMount() {
    this.props.loadProfileId(49);
  }

  updateProfile = () =>

    fetch('/api/profiles/update',
        {
          method: "PUT",
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({id:49, firstName:document.getElementById("inputFirstName").value,
            secondName:document.getElementById("inputSecondName").value,
            address:document.getElementById("inputAddress").value,
            birthDate:document.getElementById("inputBirthDate").value,
            avatarUrl:document.getElementById("inputAvatarUrl").value,
            backgroundUrl:document.getElementById("inputBackgroundUrl").value})
    });

  render() {
    return (
        <Fragment>
          <Navigation/>
          <div className="container">
            <p className="edit-profile-list">
              <input type="text" id="inputFirstName" className="edit-profile_input"
                     defaultValue={this.props.editProfile.firstName}
                     placeholder="First Name"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" id="inputSecondName" className="edit-profile_input"
                     defaultValue={this.props.editProfile.secondName}
                     placeholder="Second Name"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" id="inputAddress" className="edit-profile_input"
                     defaultValue={this.props.editProfile.address}
                     placeholder="Address"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" id="inputBirthDate" className="edit-profile_input"
                     defaultValue={this.props.editProfile.birthDate}
                     placeholder="BirthDay"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" id="inputAvatarUrl" className="edit-profile_input"
                     defaultValue={this.props.editProfile.avatarUrl}
                     placeholder="Avatar"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" id="inputBackgroundUrl" className="edit-profile_input"
                     defaultValue={this.props.editProfile.backgroundUrl}
                     placeholder="Background"/>
            </p>
            <p className="edit-profile-list">
              <input type="submit" onClick={() => this.updateProfile()}
                     className="edit-profile_button" defaultValue="hui"/>
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