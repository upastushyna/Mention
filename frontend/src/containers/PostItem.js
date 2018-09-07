import React, {Fragment} from 'react'
import more from '../img/post-form/more-icon.png'
import comment from '../img/post-form/comment-icon.png'
import forward from '../img/post-form/forward-icon.png'
import CommentContainer from './CommentContainer'
import PostLikeItem from './PostLikeItem'
import AddComment from './AddComment'
import {getDateFromDb} from '../js/timestamp.js'
import {Link} from 'react-router-dom'

const openComments = idPost => {
  let el = document.getElementById(idPost);
  let cont = el.querySelector('.comments-list');
  cont.classList.toggle('d-none');

  setTimeout(function () {
    cont.classList.toggle("comment-opacity");
  }, 100);
};

const showOptions = id => {
  if (document.getElementById('delete' + id).classList.contains('d-none')) {
    document.getElementById('delete' + id).classList.remove('d-none')
  }
};

const hide = event => {
  if (!event.target.classList.contains('post__action')) {
    let hideButton = document.getElementsByClassName('post__action');

    Array.prototype.forEach.call(hideButton, item => {
      if (!item.classList.contains('d-nome')) {
        item.classList.add('d-none')
      }
    })
  }
};

const PostItem = props => {
  window.addEventListener('mousedown', event => hide(event));
  const rePost = () => fetch('/api/posts/repost',
      {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
        },
        body: JSON.stringify({
          author: {id: props.currentUser.id},
          parent: {id: props.post.parent ? props.post.parent.id : props.post.id}
        })
      }).then(() => props.loadData(props.username));

  return <Fragment>
    <div className="post" id={props.post.id}>
      {props.post.parent
          ? <div className="repost-author">
            <Link to={'/user/' + props.post.author.username}>
              <h2 className="repost-author__info">@{props.post.author.username}</h2>
            </Link>
            reposted in
            <span className="repost-author__info">{getDateFromDb(props.post.timestamp)} </span>
          </div>
          : ''}

      <div className="post__header">
        <Link to={'/user/' + (props.post.parent
            ? props.post.parent.author.username : props.post.author.username)} className="post__link">
          <div className="profile-info d-flex-center">
            <img src={props.post.parent ? props.post.parent.author.profile.avatarUrl
                : props.post.author.profile.avatarUrl} alt="avatar" className="profile-info__avatar"/>
            <div className="profile-info__signature">
              <h2 className="profile-info__username">{props.post.parent
                  ? props.post.parent.author.username : props.post.author.username}</h2>
              <span className="profile-info__alias">
                {props.post.parent ? getDateFromDb(props.post.parent.timestamp)
                    : getDateFromDb(props.post.timestamp)}
              </span>
            </div>
          </div>
        </Link>
        {props.post.author.id === props.currentUser.id ?
            <div className="pos-relative">
              <img id={"options" + props.post.id} src={more}
                   onClick={() => showOptions(props.post.id)} alt="actions"
                   className="post__action-img" tabIndex="1"/>
              <div id={"delete" + props.post.id} className="post__action d-none"
                   onClick={() => props.deletePost({
                     id: props.post.id,
                     loadPosts: props.loadData,
                     username: props.username
                   })}>Delete post
              </div>
            </div> : ""}
      </div>
      <p className="post__body">
        {props.post.parent ? props.post.parent.body : props.post.body}
      </p>
      {props.post.parent ? props.post.parent.mediaFileUrl
          ? <img className="post__img" alt="like" src={props.post.parent.mediaFileUrl}/> : '' : props.post.mediaFileUrl
          ? <img className="post__img" alt="like-active" src={props.post.mediaFileUrl}/> : ''}
      <div className="post__footer">
        <PostLikeItem loadData={props.loadData} postId={props.post.id}
                      likes={props.post.likes} username={props.username}
                      currentUser={props.currentUser}/>
        <div className="d-flex-center">
          <img src={comment} alt="comment" className="post__action-img" onClick={() => openComments(props.post.id)}/>
          <span className="post__action-count">{props.post.comments.length}</span>
          <img onClick={() => rePost()} src={forward} alt="repost" className="post__action-img"/>
          <div className="post__action-count">{props.post.children.length}</div>
        </div>
      </div>
      <div className="comments-list d-none">
        <CommentContainer
            loadData={props.loadData}
            comments={props.post.comments}
            postId={props.post.id}
            username={props.username}
            currentUser={props.currentUser}
            deleteComment={props.deleteComment}/>
        <AddComment username={props.username} loadData={props.loadData} postId={props.post.id}
                    currentUser={props.currentUser}/>
      </div>

    </div>

  </Fragment>
}

export default PostItem