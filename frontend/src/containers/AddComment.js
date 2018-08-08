import React, {Fragment} from 'react'
import avatar from '../img/header-panel/user-img.png'

const AddComment = props =>
    <Fragment>
      <div className="add-comment">
        <div className="add-comment__form d-flex items-center">
          <img src={avatar} alt="" className="add-comment__avatar"/>
          <textarea className="add-comment__input"/>
        </div>
        <input type="submit" className="add-comment__button color-white" value="Post Comment"/>
      </div>
    </Fragment>

export default AddComment