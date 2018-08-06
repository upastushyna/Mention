import React, {Fragment} from 'react'
import user from '../img/header-panel/user-img.png'
import {Link} from 'react-router-dom'

class HeaderProfile extends React.Component {
    render() {
        return (
            <Fragment>
                <div className="header-profile container">
                    <div className="header-profile__background"/>
                    <div className="header-profile__user">
                        <img src={user} alt="" className="header-profile__img"/>
                        <h3 className="header-profile__name">Mykhail Hryhoriev</h3>
                        <h4 className="header-profile__location">Kyiv, Ukraine</h4>
                    </div>
                </div>
                <Link to="/editprofile">
                    <button className="edit-profile_button">Edit Profile!</button>
                </Link>
            </Fragment>
        )
    }
}

export default HeaderProfile