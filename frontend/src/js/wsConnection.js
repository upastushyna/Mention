import Stomp from "stompjs";
import SockJS from "sockjs-client";

export const webSocketConnection2 = (callback) =>  {
  let ws = new SockJS('http://localhost:8080/ws_0001?accessToken=' + 'Bearer '  + localStorage.getItem("accessToken"));
  let stompClient = Stomp.over(ws);
  stompClient.connect({}, function (frame) {
    stompClient.subscribe('/front/endpoint1', function (resp) {
      const object = JSON.parse(resp.body);
      callback(object.receiver, object.sender);
    });
  });
};