//import com.corundumstudio.socketio.Configuration;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.listener.ConnectListener;
//import com.corundumstudio.socketio.listener.DataListener;
//import com.corundumstudio.socketio.listener.DisconnectListener;
//import com.corundumstudio.socketio.listener.JoinLeaveListener;
//import com.corundumstudio.socketio.listener.data.JoinEventData;
//import com.corundumstudio.socketio.listener.data.Room;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.stereotype.Component;
//
//@SpringBootApplication
//public class SocketIoChatApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(SocketIoChatApplication.class, args);
//    }
//}
//
//@Component
//class SocketIOChatRunner implements CommandLineRunner {
//    @Override
//    public void run(String... args) throws Exception {
//        Configuration config = new Configuration();
//        config.setHostname("localhost");
//        config.setPort(9092);
//
//        SocketIOServer server = new SocketIOServer(config);
//
//        // Xử lý sự kiện khi người dùng kết nối
//        server.addConnectListener(new ConnectListener() {
//            @Override
//            public void onConnect(com.corundumstudio.socketio.SocketIOClient client) {
//                System.out.println("Client connected: " + client.getSessionId());
//            }
//        });
//
//        // Xử lý sự kiện khi người dùng ngắt kết nối
//        server.addDisconnectListener(new DisconnectListener() {
//            @Override
//            public void onDisconnect(com.corundumstudio.socketio.SocketIOClient client) {
//                System.out.println("Client disconnected: " + client.getSessionId());
//            }
//        });
//
//        // Xử lý sự kiện khi người dùng tham gia vào một phòng
//        server.addEventListener("joinRoom", Room.class, new DataListener<Room>() {
//            @Override
//            public void onData(com.corundumstudio.socketio.SocketIOClient client, Room data, AckRequest ackRequest) {
//                String roomName = data.getRoomName();
//                client.joinRoom(roomName);
//                System.out.println("Client " + client.getSessionId() + " joined room: " + roomName);
//            }
//        });
//
//        // Xử lý sự kiện khi người dùng gửi tin nhắn trong phòng
//        server.addEventListener("chatMessage", ChatMessage.class, new DataListener<ChatMessage>() {
//            @Override
//            public void onData(com.corundumstudio.socketio.SocketIOClient client, ChatMessage data, AckRequest ackRequest) {
//                String roomName = data.getRoomName();
//                String message = data.getMessage();
//                System.out.println("Client " + client.getSessionId() + " sent message in room " + roomName + ": " + message);
//                server.getRoomOperations(roomName).sendEvent("chatMessage", data);
//            }
//        });
//
//        server.start();
//    }
//}
//
//class Room {
//    private String roomName;
//
//    public String getRoomName() {
//        return roomName;
//    }
//
//    public void setRoomName(String roomName) {
//        this.roomName = roomName;
//    }
//}
//
//class ChatMessage {
//    private String roomName;
//    private String message;
//
//    public String getRoomName() {
//        return roomName;
//    }
//
//    public void setRoomName(String roomName) {
//        this.roomName = roomName;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//}