import React from 'react'
import Navigation from "./Navigation";
import EditUserProfile from "../containers/EditUserProfile"

export default class EditProfile extends React.Component {
    render() {
        return (
            <div>
                <Navigation/>
                <EditUserProfile/>
            </div>
        )
    }
}