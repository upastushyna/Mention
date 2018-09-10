import Stomp from "stompjs";
import SockJS from "sockjs-client";

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
      callback(object);
    });
  });
};