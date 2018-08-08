import React, { Fragment } from 'react'
import Navigation from "./Navigation"
import '../css/index.css'
import PostItem from '../containers/PostItem'
import {connect} from 'react-redux'
import {loadPosts} from "../actions/userPageActions";
import HeaderProfile from "../containers/HeaderProfile";

class UserPage extends React.Component {

  componentWillMount(){
    if(this.props.userPosts.length === 0) {
      this.props.loadData(this.props.match.params.username);
    }
  }

  render() {
    const posts = this.props.userPosts.map(post =>
      <PostItem post={post}/>)
    return (
      <Fragment>
        <Navigation/>
        <div className="container">
         <HeaderProfile/>
          {posts}
        </div>
      </Fragment>

    );
  }
}

const mapStateToProps = state => ({
  userPosts: state.userPosts,

});

const mapDispatchToProps = dispatch => ({
  loadData: username => dispatch(loadPosts(username))
});

export default connect(mapStateToProps, mapDispatchToProps)(UserPage);