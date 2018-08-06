import React, { Fragment } from 'react'
import LikeItem from './LikeItem'
import Comments from './Comments'

const PostItem2 = props =>
<Fragment>
  <h2>{props.post.author.username}</h2>
  <img src={props.post.author.profile.avatarUrl}/>
  <p>{props.post.body}</p>
  <h2>{props.post.timestamp}</h2>
  <LikeItem likes={props.post.likes}/>
  <Comments comments={props.post.comments}/>
</Fragment>

export default PostItem2