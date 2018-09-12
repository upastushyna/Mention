import Stomp from "stompjs";
import SockJS from "sockjs-client";
import {COMMENT, COMMENT_LIKE, FOLLOW, MESSAGE, POST, POST_LIKE} from "../constants/action-types";

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

export const webSocketMessageNotification = (callback) => {
  let ws = new SockJS('http://localhost:8080/ws_0001?accessToken=' +
    'Bearer '  + localStorage.getItem("accessToken"));
  let stompClient = Stomp.over(ws);
  stompClient.connect({}, function (frame) {
    stompClient.subscribe('/user/queue/notifications', function (resp) {
      const object = JSON.parse(resp.body);
      switch (object.type) {
        case MESSAGE:
          if (window.location.pathname !== '/messages/' + object.sender.username) {
            object.url = `/messages/${object.sender.username}`;
            object.message = `New message from ${object.sender.username}`;
            callback(object);
          }
          break;
        case COMMENT:
          object.url = `/post/${object.post.id}`;
          object.message = `${object.sender.username} has commented your post`;
          callback(object);
          break;
        case POST:
          if (window.location.pathname !== '/') {
            object.url = `/post/${object.post.id}`;
            object.message = `${object.sender.username} shared a new post`;
            callback(object);
          }
          break;
        case FOLLOW:
          object.url = `/user/${object.sender.username}`;
          object.message = `${object.sender.username} is now following you`;
          callback(object);
          break;
        case POST_LIKE:
          object.url = `/post/${object.post.id}`;
          object.message = `${object.sender.username} has liked your post`;
          callback(object);
          break;
        case COMMENT_LIKE:
          object.url = `/post/${object.post.id}`;
          object.message = `${object.sender.username} has liked your comment`;
          callback(object);
      }
    });
  });
};
/*

function WebSocket(subsribeUrl, ArrayOfChannels) {
  const socket = SockJS(subsribeUrl); //create wrapper
  const stompClient = Stomp.over(socket);//connect using your client
  stompClient.connect({}, () => {
    ArrayOfChannels.forEach((channel) => {
      stompClient.subscribe(channel.route, channel.callback);
    });
  }, () => {
    setTimeout(() => {
      subscribeToSocket(subsribeUrl, ArrayOfChannels);
    }, 0);
  });
}*/
