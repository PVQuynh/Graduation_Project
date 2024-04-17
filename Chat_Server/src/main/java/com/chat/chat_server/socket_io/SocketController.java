//package com.chat.chat_server.socket_io;
//
//import com.chat.chat_server.dto.request.MessageReq;
//import com.chat.chat_server.service.MessageService;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.listener.ConnectListener;
//import com.corundumstudio.socketio.listener.DataListener;
//import com.corundumstudio.socketio.listener.DisconnectListener;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class SocketController {
//
//    private final SocketIOServer socketIOServer;
//
//    @Autowired
//    private MessageService messageService;
//
//    // Tạo SocketIOServer để lắng nghe các sự kiện
//    public SocketController(SocketIOServer socketIOServer) {
//        this.socketIOServer = socketIOServer;
//
//        socketIOServer.addConnectListener(onConnected());
//        socketIOServer.addDisconnectListener(onDisconnected());
//        socketIOServer.addEventListener("send_message", MessageReq.class, onMessageReceived());
//    }
//
//    // Xử lý kết nối
//    public ConnectListener onConnected() {
//        return client -> {
//            String conversationId = client.getHandshakeData().getSingleUrlParam("conversationId");
//            String contactId = client.getHandshakeData().getSingleUrlParam("contactId");
//
//           if (contactId != null && conversationId != null) {
//               client.getSessionId();
//               client.joinRoom(conversationId);
//
//               try {
//                   messageService.setSeenForMessageByContactId(Integer.parseInt(conversationId), Integer.parseInt(contactId));
//               } catch (Exception ex) {
//                   client.getNamespace().getRoomOperations(conversationId)
//                           .sendEvent("get_message", String.format("Yêu cầu bạn đăng nhập để xét trạng thái tin nhắn!"));
//               }
//           } else {
//               client.disconnect();
//           }
//        };
//    }
//
//    // Nhận tin nhắn và gửi lại cho phòng
//    public DataListener<MessageReq> onMessageReceived() {
//
//        return (senderClient, messageReq, ackSender) -> {
//            String conversationId = senderClient.getHandshakeData().getSingleUrlParam("conversationId");
//
//            senderClient.getNamespace().getRoomOperations(conversationId).getClients().forEach(
//                    x -> {
//                        if (!x.getSessionId().equals(senderClient.getSessionId())) {
//                            x.sendEvent("get_message", messageReq);
//                        }
//                    }
//            );
//
//            messageService.saveMessage(Long.parseLong(conversationId), messageReq);
//        };
//    }
//
//    // Xử lý ngắt kết nối
//    public DisconnectListener onDisconnected() {
//        return client -> {
//            String conversationId = client.getHandshakeData().getSingleUrlParam("conversationId");
//            String contactId = client.getHandshakeData().getSingleUrlParam("contactId");
//
//            if (contactId !=null && conversationId !=null) {
//                try {
//                    messageService.setSeenForMessageByContactId(Integer.parseInt(conversationId), Integer.parseInt(contactId));
//                } catch (Exception ex) {
//                    client.getNamespace().getRoomOperations(conversationId)
//                            .sendEvent("get_message", "Yêu cầu bạn đăng nhập để xét trạng thái tin nhắn!");
//                }
//            }
//        };
//    }
//
//}
