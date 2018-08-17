import React, {Fragment} from 'react'
import Navigation from './Navigation'
import {connect} from "react-redux";
import {loadSearchPosts} from "../actions/searchPostsActions";
import {loadSearchUsers} from "../actions/searchUsersActions";
import PostsContainer from "../containers/PostsContainer";
import UsersContainer from "../containers/UsersContainer";

class SearchPage extends React.Component {

  componentWillMount(){
    if(this.props.foundPosts.length === 0 && this.props.foundUsers.length === 0) {
      console.log(this.props);
      this.props.loadPosts(this.props.match.params.input);
      this.props.loadUsers(this.props.match.params.input);
    }
  }

  render () {

    return (
      <Fragment>
        <Navigation/>
        <div className="container">
          {this.props.foundPosts.length === 0 ? "Sorry, no posts found matching your search" :
            <PostsContainer username={this.props.match.params.input}
                            userPosts={this.props.foundPosts}
                            loadData={this.props.loadPosts}/>}
           <div className="users-panel">
               {this.props.foundUsers.length === 0 ? "" :
                   <UsersContainer username={this.props.match.params.input}
                                   loadUsers={this.props.loadUsers}
                                   users={this.props.foundUsers}/>}
           </div>
        </div>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  foundPosts: state.foundPosts,
  foundUsers: state.foundUsers

});

const mapDispatchToProps = dispatch => ({
  loadPosts: input => dispatch(loadSearchPosts(input)),
  loadUsers: input => dispatch(loadSearchUsers(input))
});

export default connect(mapStateToProps, mapDispatchToProps)(SearchPage);