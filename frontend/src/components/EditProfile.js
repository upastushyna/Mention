import React, {Fragment} from 'react'
import {loadProfileById} from '../actions/editProfileAction'
import {connect} from 'react-redux'
import Navigation from './Navigation'
import DatePicker from 'react-datepicker/es/index'
import 'react-datepicker/dist/react-datepicker.css'
import moment from 'moment'

class EditProfile extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      startDate: undefined
    }
    this.handleChange = this.handleChange.bind(this)
  }

  handleChange(date) {
    this.setState({
      startDate: date
    })
  }

  componentWillMount() {
    if (this.props.currentUser.id) {
      this.props.loadProfileById(this.props.currentUser.id);
    }
  }

  componentDidUpdate() {
    if (this.props.editProfile.birthDate && this.state.startDate === undefined)
      this.setState({startDate: moment(this.props.editProfile.birthDate)});
  }


  updateProfile = () =>

      fetch('/api/profiles/update',
          {
            method: 'PUT',
            headers: {
              'Authorization': "Bearer " + localStorage.getItem("accessToken"),
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              id: this.props.editProfile.id,
              firstName: document.getElementById('inputFirstName').value,
              secondName: document.getElementById('inputSecondName').value,
              address: document.getElementById('inputAddress').value,
              birthDate: this.state.startDate,
              avatarUrl: document.getElementById('inputAvatarUrl').value,
              backgroundUrl: document.getElementById('inputBackgroundUrl').value,
              user: {id: this.props.currentUser.id}
            })
          })

  render() {
    if (!this.props.editProfile) {
      return "LOADIN...."
    }

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
            {/*<p className="edit-profile-list">*/}
            {/*<input type="text" id="inputBirthDate" className="edit-profile_input"*/}
            {/*defaultValue={this.props.editProfile.birthDate}*/}
            {/*placeholder="BirthDay"/>*/}
            {/*</p>*/}
            <DatePicker
                selected={this.state.startDate}
                onChange={this.handleChange}
                peekNextMonth
                showMonthDropdown
                showYearDropdown
                dropdownMode="select"
            />
            <p className="edit-profile-list">
              <input type="text" id="inputAvatarUrl" className="edit-profile_input"
                     defaultValue={this.props.editProfile.avatarUrl}
                     placeholder="Avatar"/>
              <input type="submit" onClick={this.submitAvatar} defaultValue="Change Avatar"/>
            </p>
            <p className="edit-profile-list">
              <input type="text" id="inputBackgroundUrl" className="edit-profile_input"
                     defaultValue={this.props.editProfile.backgroundUrl}
                     placeholder="Background"/>
              <input type="submit" onClick={this.submitBackground} defaultValue="Change Background"/>
            </p>
            <p className="edit-profile-list">
              <input type="submit" onClick={() => this.updateProfile()}
                     className="edit-profile_button" defaultValue="Edit"/>
            </p>
          </div>
        </Fragment>
    )
    /*if (!this.props.editProfile || !this.props.editProfile.birthDate) {
      return "Loading..."
    }*/
    /*this.setState({startDate:moment(this.props.editProfile.birthDate)});
*/
  }
}

const mapStateToProps = state => ({
  editProfile: state.editProfile
})

const mapDispatchToProps = dispatch => ({
  loadProfileById: (id) => dispatch(loadProfileById(id))
})

export default connect(mapStateToProps, mapDispatchToProps)(EditProfile)