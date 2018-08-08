import store from "../store/index.js";
import {loadFeed} from "../actions/feedActions";
import {loadProfile} from "../actions/editProfileAction";

window.store = store;
window.loadFeed = loadFeed;
window.loadProfile = loadProfile;