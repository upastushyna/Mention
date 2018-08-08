import store from "../store/index.js";
import { loadFeed } from "../actions/feedActions";

window.store = store;
window.loadFeed = loadFeed;