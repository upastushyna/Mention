import React, {Fragment} from 'react'

export default class EditUserProfile extends React.Component {
    render() {
        return (
            <Fragment>
                <div className="container">
                    <p className="edit-profile-list">
                        <input type="text" className="edit-profile_input" placeholder="firstname"/>
                    </p>
                    <p className="edit-profile-list">
                        <input type="text" className="edit-profile_input" placeholder="secondname"/>
                    </p>
                    <p className="edit-profile-list">
                        <input type="text" className="edit-profile_input" placeholder="address"/>
                    </p>
                    <p className="edit-profile-list">
                        <input type="text" className="edit-profile_input" placeholder="birthdate"/>
                    </p>
                    <p className="edit-profile-list">
                        <input type="text" className="edit-profile_input" placeholder="avatar"/>
                    </p>
                    <p className="edit-profile-list">
                        <input type="text" className="edit-profile_input" placeholder="background"/>
                    </p>
                    <p className="edit-profile-list">
                        <input type="submit" className="edit-profile_button" value="Edit"/>
                    </p>
                </div>
            </Fragment>
        )
    }
}