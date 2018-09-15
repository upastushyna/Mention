import Stomp from "stompjs";
import SockJS from "sockjs-client";
import {COMMENT, COMMENT_LIKE, FOLLOW, MESSAGE, POST, POST_LIKE, REPOST} from "../constants/action-types";

export const webSocketChat = (callback1, callback2) =>  {
  let ws = new SockJS('http://localhost:8080/ws_0001?accessToken=' +
    'Bearer '  + localStorage.getItem("accessToken"));
  let stompClient = Stomp.over(ws);
  stompClient.connect({}, function (frame) {
    stompClient.subscribe('/user/queue/chat', function (resp) {
      const object = JSON.parse(resp.body);
      callback1(object.receiver, object.sender);
      callback2();
    });
  });
};

export const webSocketFeed = (callback) => {
  let ws = new SockJS('http://localhost:8080/ws_0001?accessToken=' +
    'Bearer '  + localStorage.getItem("accessToken"));
  let stompClient = Stomp.over(ws);
  stompClient.connect({}, function (frame) {
    stompClient.subscribe('/user/queue/feed', function (resp) {
      const object = JSON.parse(resp.body);
      callback(object.listener);
    });
  });
};

export const webSocketPopUpNotification = (callback1, callback2, callback3, history) => {
  let ws = new SockJS('http://localhost:8080/ws_0001?accessToken=' +
    'Bearer '  + localStorage.getItem("accessToken"));
  let stompClient = Stomp.over(ws);
  stompClient.connect({}, function (frame) {
    stompClient.subscribe('/user/queue/notifications', function (resp) {
      const object = JSON.parse(resp.body);
      switch (object.type) {
        case MESSAGE:
          if (history.location.pathname !== '/messages/' + object.sender.username) {
            object.url = `/messages/${object.sender.username}`;
            object.message = `New message from ${object.sender.username}`;
          } else {
            callback3(callback2, object.id);
            return;
          }
          break;
        case COMMENT:
          object.url = `/post/${object.post.id}`;
          object.message = `${object.sender.username} has commented your post`;
          break;
        case POST:
          if (history.location.pathname !== '/') {
            object.url = `/post/${object.post.id}`;
            object.message = `${object.sender.username} shared a new post`;
          } else {
            callback3(callback2, object.id)
            return;
          }
          break;
        case FOLLOW:
          object.url = `/user/${object.sender.username}`;
          object.message = `${object.sender.username} is now following you`;
          break;
        case POST_LIKE:
          object.url = `/post/${object.post.id}`;
          object.message = `${object.sender.username} has liked your post`;
          break;
        case COMMENT_LIKE:
          object.url = `/post/${object.post.id}`;
          object.message = `${object.sender.username} has liked your comment`;
          break;
        case REPOST:
          object.url = `/post/${object.post.id}`;
          object.message = `${object.sender.username} has re-posted you`;
      }
      callback1(object);
      callback2();
    });
  });
};
