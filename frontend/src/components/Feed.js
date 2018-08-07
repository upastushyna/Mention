import React, { Fragment } from 'react'
import Navigation from "./Navigation"
import '../css/index.css'
import PostItem from '../containers/PostItem'
import {loadFeed} from "../actions/feedActions";
import {connect} from 'react-redux'

class Feed extends React.Component {

  componentWillMount(){
    if(this.props.feed.length === 0) {
      this.props.loadData("admin");
    }
  }

  /*addPost = () => fetch('/api/post',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({body:document.getElementById("input").value, author:{id:1}})
    })*/



  render () {
    const feed = this.props.feed.map(post =>
      <PostItem post={post}/>)
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          {feed}
        </div>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  feed: state.feed,

});

const mapDispatchToProps = dispatch => ({
  loadData: id => dispatch(loadFeed(id))
});

export default connect(mapStateToProps, mapDispatchToProps)(Feed);