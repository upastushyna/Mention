import React, { Fragment } from 'react'
import Navigation from "./Navigation"
import '../css/index.css'
import PostItem from '../containers/PostItem'
import {loadFeed} from "../actions/feedActions";
import {connect} from 'react-redux'
import {USERNAME, ID} from "../constants/hardcode";


const username = "admin";
const id = 1;
class Feed extends React.Component {

  componentWillMount(){
    if(this.props.feed.length === 0) {
      this.props.loadData(USERNAME);
    }
  }

  addPost = () => fetch('/api/posts/add',
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({body:this.refs.postInput
          .value, author:{id:ID}})
    }).then(() => this.refs.postInput.value="");

  render () {
    const feed = this.props.feed.map(post =>
      <PostItem username={USERNAME} loadData={this.props.loadData} post={post}/>);
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          <input id="postInput" type="text" placeholder="Share your thoughts" ref="postInput"
                 maxLength={255}/><button onClick={() => this.addPost()}>Add new post</button>
          {feed}
        </div>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  feed: state.feed
});

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadFeed(username))
});

export default connect(mapStateToProps, mapDispatchToProps)(Feed);