export const connect = () => {
  var socket = new SockJS('/ws_0001');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    stompClient.subscribe('/front/endpoint1', function (resp) {
      processResponse(resp.body);
    });
  });
};

function processResponse(body) {
  object = JSON.parse(body);
  html = "<tr><td>" + object.content + "</td><td>" + object.id + "</td></tr>"
}