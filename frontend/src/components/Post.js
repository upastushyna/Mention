import React, { Fragment } from 'react'
import PostItem from '../containers/PostItem'
import {deletePost, loadPostById} from '../actions/postsActions'
import {connect} from 'react-redux'
import {deleteComment} from '../actions/commentsActions'
import Preloader from '../containers/Preloader'

class Post extends React.Component {
  componentWillMount () {
    if (!this.props.post || !this.props.post.author) {
      this.props.loadPost(this.props.match.params.id)
    }
  }

  componentDidUpdate () {
    this.callUpdate()
  }

  componentDidMount () {
    this.callUpdate()
  }

  callUpdate = () => {
    if (!this.props.post) {
      this.props.history.push('/')
    } else if (this.props.post.id != this.props.match.params.id) {
      this.props.loadPost(this.props.match.params.id)
    }
  }

  render () {
    if (!this.props.post || !this.props.post.author) {
      return <Preloader/>
    }
    return (
      <Fragment>
          <PostItem username={this.props.match.params.id}
            loadData={this.props.loadPost}
            post={this.props.post}
            currentUser={this.props.currentUser}
            deletePost={this.props.deletePost}
            key={this.props.post.id}
            deleteComment={this.props.deleteComment}/>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  post: state.post
})

const mapDispatchToProps = dispatch => ({
  loadPost: id => dispatch(loadPostById(id)),
  deletePost: data => dispatch(deletePost(data)),
  deleteComment: data => dispatch(deleteComment(data))
})
export default connect(mapStateToProps, mapDispatchToProps)(Post)