package com.chat.websocket.socket_io;

import com.chat.websocket.dto.request.MessageReq;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketController {
    private final SocketIOServer socketIOServer;

    // Tạo SocketIOServer để lắng nghe các sự kiện
    public SocketController(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;

        socketIOServer.addConnectListener(onConnected());
        socketIOServer.addDisconnectListener(onDisconnected());
        socketIOServer.addEventListener("send_message", MessageReq.class, onMessageReceived());
    }

    // Nhận tin nhắn và gửi lại cho phòng
    private DataListener<MessageReq> onMessageReceived() {

        return (senderClient, data, ackSender) -> {
            String room = senderClient.getHandshakeData().getSingleUrlParam("conversation");

            senderClient.getNamespace().getRoomOperations(room).getClients().forEach(
                    x -> {
                        if (!x.getSessionId().equals(senderClient.getSessionId())) {
                            x.sendEvent("get_message", data);
                        }
                    }
            );
        };
    }

    // Xử lý kết nối
    private ConnectListener onConnected() {
        return client -> {
            String room = client.getHandshakeData().getSingleUrlParam("conversation");
            client.joinRoom(room);
//            client.getNamespace().getRoomOperations(room)
//                    .sendEvent("get_message", String.format("%s connected to -> %s",
//                            client.getSessionId(), room
//                    ));

//            log.info(String.format("SocketID: %s connected", client.getSessionId().toString()));
        };
    }

    // Xử lý ngắt kết nối
    private DisconnectListener onDisconnected() {
        return client -> {

            String room = client.getHandshakeData().getSingleUrlParam("room");

//            client.getNamespace().getRoomOperations(room)
//                    .sendEvent("get_message", String.format("%s disconnected from -> %s",
//                            client.getSessionId(), room
//                    ));
//
//            log.info(String.format("SocketID: %s disconnected!", client.getSessionId().toString()));
        };
    }
}
