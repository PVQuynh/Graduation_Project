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
    public DataListener<MessageReq> onMessageReceived() {

        return (senderClient, data, ackSender) -> {
            String conversation = senderClient.getHandshakeData().getSingleUrlParam("conversation");

            senderClient.getNamespace().getRoomOperations(conversation).getClients().forEach(
                    x -> {
                        if (!x.getSessionId().equals(senderClient.getSessionId())) {
                            x.sendEvent("get_message", data);
                        }
                    }
            );
        };
    }

    // Xử lý kết nối
    public ConnectListener onConnected() {
        return client -> {
            String conversation = client.getHandshakeData().getSingleUrlParam("conversation");
            client.joinRoom(conversation);
//            client.getNamespace().getRoomOperations(conversation)
//                    .sendEvent("get_message", String.format("%s connected to -> %s",
//                            client.getSessionId(), conversation
//                    ));

//            log.info(String.format("SocketID: %s connected", client.getSessionId().toString()));
        };
    }

    // Xử lý ngắt kết nối
    public DisconnectListener onDisconnected() {
        return client -> {

            String conversation = client.getHandshakeData().getSingleUrlParam("conversation");

//            client.getNamespace().getRoomOperations(conversation)
//                    .sendEvent("get_message", String.format("%s disconnected from -> %s",
//                            client.getSessionId(), conversation
//                    ));
//
//            log.info(String.format("SocketID: %s disconnected!", client.getSessionId().toString()));
        };
    }
}
