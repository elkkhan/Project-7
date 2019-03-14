package tos.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    public static void main(String[] args){

        try (ServerSocket serverSocket = new ServerSocket(2020)) {
            ScreenShower.writeMessage("Server started...");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (Exception e) {
            ScreenShower.writeMessage("Something wrong, Server socket closed.");
        }
    }
    private static class Handler extends Thread{
        public Socket socket;
        String username;
        public void run(){
            ScreenShower.writeMessage("New Connection " + socket.getRemoteSocketAddress());

            try(Connection connection = new Connection(socket)) {

                username = getName(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED,username));
                notifyUsers(connection,username);
                serverMainLoop(connection,username);

            } catch (IOException  | ClassNotFoundException e) {
                ScreenShower.writeMessage("An exchange of data error to a remote socket address");
            }

        }

        private String getName(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();

                if (answer.getType() == MessageType.USER_NAME) {

                    return answer.getData();

                }
            }
        }
        private void notifyUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                if (!entry.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while(true){
                Message message = connection.receive();
                if(message.getType()==MessageType.TEXT){
                    String str = userName+": "+ message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT,str));
                }
                else ScreenShower.writeMessage("Something wrong, can't send a message");

            }

        }

        public Handler(Socket socket){
            this.socket=socket;
        }
    }

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message){
        for(Connection connection : connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                ScreenShower.writeMessage("Something wrong, can't send a message");
            }
        }
    }



}
