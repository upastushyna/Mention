import React, { Fragment } from 'react'
import avatar from '../img/header-panel/user-img.png'
import more from '../img/more-icon.png'
import like from '../img/like-icon.png'
import comment from '../img/comment-icon.png'

const PostItem = props =>
  <Fragment>
    {/*<img src={this.props.avatar}/>
    <h2>{this.props.username}</h2>
    <p>{this.props.body}</p>*/}
    <div className="post">
      <div className="post__header d-flex content-between items-center">
        <div className="profile d-flex">
          <img src={avatar} alt="" className="profile__avatar"/>
          <div className="profile__signature">
            <h2 className="profile__username color-dark-grey">Mykhail Hryhoriev</h2>
            <span className="profile__alias">19 hours ago</span>
          </div>
        </div>
        <div className="post__more-icon">
          <img src={more} alt="" className="post__more-img"/>
        </div>
      </div>
      <p className="post__body">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit.
        Accusantium assumenda blanditiis cum deleniti deserunt doloribus eaque
        eius esse facere illo incidunt odio, provident, rerum similique sint
        suscipit, veniam voluptate voluptates!
      </p>
      <div className="post__footer d-flex content-between">
        <div className="post__like-icon d-flex items-center">
          <img src={like} alt="" className="post__like-img"/>
          <span className="post__like-number">5</span>
        </div>
        <div className="post__comment-icon d-flex items-center">
          <img src={comment} alt="" className="post__comment-img"/>
          <span className="post__comment-number">5</span>
        </div>
      </div>
    </div>
    <div className="comment-container">
      <div className="comment-container__form d-flex items-center">
        <img src={avatar} alt="" className="comment-container__avatar"/>
        <textarea className="comment-container__input"/>
      </div>
      <input type="submit" className="comment-container__button color-white" value="Post Comment"/>
    </div>
  </Fragment>

export default PostItem