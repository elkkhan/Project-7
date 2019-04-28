package tos.chat;

import static tos.chat.MessageType.TEXT;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

  private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket();
    ScreenShower.writeMessage("Please input server port: ");
    String servport = ScreenShower.readString();
    while (!isInteger(servport)) {
      System.out.println("Your port number should consist only digits!");
      servport = ScreenShower.readString();
    }
    int port = Integer.valueOf(servport);
    while (!isCorrectPort(port)) {
      System.out.println("Your port number has to be in range from 1024 to 65535!");
      port = ScreenShower.readInt();
    }
    serverSocket = new ServerSocket(port);

    ScreenShower.writeMessage("Server " + port + " started...");
    while (true) {
      new Handler(serverSocket.accept()).start();
    }
  }

  public static boolean isInteger(String num) {
    int size = num.length();

    if (num == null) {
      return false;
    }

    for (int i = 0; i < size; i++) {
      if (!Character.isDigit(num.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean isCorrectPort(int port) {
    if (port > 1024 && port < 65535) {
      return true;
    }
    return false;
  }

  public static boolean isTextType(MessageType messageType) {
    if (messageType == TEXT) {
      return true;
    } else {
      return false;
    }
  }

  public static void sendBroadcastMessage(Message message) {
    for (Connection connection : connectionMap.values()) {
      try {
        connection.send(message);
        // ScreenShower.writeMessage(message+" это моя проверка что значит message");
      } catch (IOException e) {
        ScreenShower.writeMessage("Something wrong, can't send a message");
      }
    }
  }

  private static class Handler extends Thread {

    public Socket socket;
    String username;

    public Handler(Socket socket) {
      this.socket = socket;
    }

    public void run() {
      ScreenShower.writeMessage("New Connection " + socket.getRemoteSocketAddress());

      try (Connection connection = new Connection(socket)) {

        username = clientCon(connection);
        sendBroadcastMessage(new Message(MessageType.USER_ADDED, username));
        notifyUsers(connection, username);
        serverLoop(connection, username);

      } catch (IOException | ClassNotFoundException e) {
        ScreenShower.writeMessage("An exchange of data error to a remote socket address");
      } finally {
        if (username != null) {
          connectionMap.remove(username);
          sendBroadcastMessage(new Message(MessageType.USER_REMOVED, username));
        }
        ScreenShower.writeMessage(
            "Closed connection to a remote socket address: "
                + socket.getInetAddress()); // + socketAddress);
      }
    }

    private String clientCon(Connection connection) throws IOException, ClassNotFoundException {
      while (true) {
        connection.send(new Message(MessageType.NAME_REQUEST));
        Message answer = connection.receive();
        // ScreenShower.writeMessage(String.valueOf(connection.receive()));
        System.out.println(answer);

        if (answer.getType() == MessageType.USER_NAME) {

          if (!answer.getData().isEmpty()) {

            if (!connectionMap.containsKey(answer.getData())) {
              connectionMap.put(answer.getData(), connection);
              connection.send(new Message(MessageType.NAME_ACCEPTED));
              return answer.getData();
            }
          }
        }
      }
    }

    private void notifyUsers(Connection connection, String userName) throws IOException {
      for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
        if (!entry.getKey().equals(userName)) {
          connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
        }
      }
    }

    private void serverLoop(Connection connection, String userName)
        throws IOException, ClassNotFoundException {
      while (true) {
        Message message = connection.receive();
        /*if(message.getType()== TEXT){
            String str = userName+": "+ message.getData();
            System.out.println(userName + message.getType());
            sendBroadcastMessage(new Message(TEXT,str));
        }

        */
        if (isTextType(message.getType())) {
          String str = userName + ": " + message.getData();

          sendBroadcastMessage(new Message(TEXT, str));
        } else {
          ScreenShower.writeMessage("Something wrong, can't send a message");
        }
      }
    }
  }
}
