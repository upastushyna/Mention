import React, {Fragment} from 'react'
import {loadProfileById} from '../actions/editProfileAction'
import {connect} from 'react-redux'
import Navigation from './Navigation'
import DatePicker from 'react-datepicker/es/index'
import 'react-datepicker/dist/react-datepicker.css'
import moment from 'moment'
import upload from '../img/fileuploadicon.png'

class EditProfile extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      startDate: undefined
    };
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


  updateProfile = event => {
    event.preventDefault();

    fetch('/api/profiles/update',
      {
        method: 'PUT',
        headers: {
          'Authorization': "Bearer " + localStorage.getItem("accessToken"),
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({id:this.props.editProfile.id,
          firstName:this.refs.inputFirstName.value,
          secondName:this.refs.inputSecondName.value,
          address:this.refs.inputAddress.value,
          birthDate:this.state.startDate,
          avatarUrl:this.props.editProfile.avatarUrl,
          avatarKey:this.props.editProfile.avatarKey,
          backgroundUrl:this.props.editProfile.backgroundUrl,
          backgroundKey:this.props.editProfile.backgroundKey,
          user:{id:this.props.currentUser.id}})
      }).then(this.props.loadProfileById(this.props.currentUser.id));
  };

  updateAvatar = event => {
    event.preventDefault();
    const data = new FormData();
    if(this.refs.avatarFile) {
      const image = this.refs.avatarFile.files[0];
      data.append("image", image);

      fetch('/api/profiles/avatar',
        {
          method: 'PUT',
          headers: {
            'Authorization': "Bearer " + localStorage.getItem("accessToken")
          },
          body: data
        }).then(this.refs.addAvatar.innerText = "Add file")
        .then(this.props.loadCurrentUser())
    }
  };

  updateBackground = event => {
    event.preventDefault();
    const data = new FormData();
    if(this.refs.backgroundFile) {
      const image = this.refs.backgroundFile.files[0];
      data.append("image", image);

      fetch('/api/profiles/background',
        {
          method: 'PUT',
          headers: {
            'Authorization': "Bearer " + localStorage.getItem("accessToken")
          },
          body: data
        }).then(this.refs.addBackground.innerText = "Add file")
        .then(this.props.loadCurrentUser())
    }
  };

  changeBackgroundName = () => {
    if (this.refs.backgroundFile.files[0]) {
      this.refs.addBackground.innerText = this.refs.backgroundFile.files[0].name;
    } else {
      this.refs.addBackground.innerText = "Add file";
    }
  };

  changeAvatarName = () => {
    if (this.refs.avatarFile.files[0]) {
      this.refs.addAvatar.innerText = this.refs.avatarFile.files[0].name;
    } else {
      this.refs.addAvatar.innerText = "Add file";
    }
  };

  render() {
    if (!this.props.editProfile) {
      return "Loading..."
    }

    return (
      <Fragment key={EditProfile.id}>
        <Navigation/>
        <div className="container">
          <form className="edit-profile flex-column" onSubmit={event => this.updateProfile(event)}>
            <h2 className="edit-profile__title">Tell us more about yourself</h2>
              <div className="edit-profile__item">
                <p className="edit-profile__title_secondary">Change firstname</p>
                <input type="text" ref="inputFirstName" className="input_custom edit-profile__input"
                           defaultValue={this.props.editProfile.firstName}
                           placeholder="First Name"/>
              </div>
              <div className="edit-profile__item">
                <p className="edit-profile__title_secondary">Change secondname</p>
                <input type="text" ref="inputSecondName" className="input_custom edit-profile__input"
                           defaultValue={this.props.editProfile.secondName}
                           placeholder="Second Name"/>
              </div>
              <div className="edit-profile__item">
                <p className="edit-profile__title_secondary">Change address</p>
                <input type="text" ref="inputAddress" className="input_custom edit-profile__input"
                           defaultValue={this.props.editProfile.address}
                           placeholder="Address"/>
              </div>
              <div className="edit-profile__item">
                <p className="edit-profile__title_secondary">Change birthday</p>
                <DatePicker
                      selected={this.state.startDate}
                      onChange={this.handleChange}
                      peekNextMonth
                      showMonthDropdown
                      showYearDropdown
                      dropdownMode="select"
                      className="input_custom edit-profile__input"
                      placeholderText="Select birthday"
                  />
              </div>
              <div className="edit-profile__item">
                <input type="submit" className="edit-profile__btn btn-action" defaultValue="Edit"/>
              </div>
          </form>
          <form className="edit-profile flex-column" encType="multipart/form-data"
                onSubmit={event => this.updateAvatar(event)}>
              <div className="edit-profile__item">
                <p className="edit-profile__title_secondary">Change avatar</p>
                <div className="upload-file">
                  <img src={upload} alt="upload" className="upload-file__icon"/>
                  <p ref="addAvatar">Add file</p>
                  <input onChange={() => this.changeAvatarName()} className="upload"
                         ref="avatarFile"
                         type="file"/>
                </div>
              </div>
              <div className="edit-profile__item">
                <input type="submit" className="edit-profile__btn btn-action"
                       defaultValue="Change Profile Picture"/>
              </div>
          </form>
          <form className="edit-profile flex-column" encType="multipart/form-data"
                onSubmit={event => this.updateBackground(event)}>
              <div className="edit-profile__item">
                <p className="edit-profile__title_secondary">Change profile background</p>
                <div className="upload-file">
                  <img src={upload} alt="upload" className="upload-file__icon"/>
                  <p ref="addBackground">Add file</p>
                  <input onChange={() => this.changeBackgroundName()}
                         className="upload"
                         ref="backgroundFile"
                         type="file"/>
                </div>
              </div>
              <div className="edit-profile__item">
                <input type="submit" className="edit-profile__btn btn-action"
                       defaultValue="Change background picture"/>
              </div>
          </form>
        </div>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  editProfile: state.editProfile
});

const mapDispatchToProps = dispatch => ({
  loadProfileById: (id) => dispatch(loadProfileById(id))
});

export default connect(mapStateToProps, mapDispatchToProps)(EditProfile)