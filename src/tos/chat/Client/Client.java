package tos.chat.Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import tos.chat.Connection;
import tos.chat.Message;
import tos.chat.MessageType;
import tos.chat.ScreenShower;

public class Client {

  protected Connection connection;
  Scanner in = new Scanner(System.in);
  private volatile boolean clientConnected = false;

  public static void main(String[] args) {
    Client client = new Client();

    client.runSocketThread();
  }

  public boolean isValidIP(String ip) {
    String[] parts = ip.split("\\.");

    if (ip.equals("localhost")) {
      return true;
    } else if (parts.length == 4) {
      int flag = 0;
      for (int i = 0; i < 4; i++) {
        if (parts[i].length() > 0 && parts[i].length() < 4) {
          flag++;
        }
      }
      System.out.println(flag);
      return flag == 4;
    } else {

      return false;
    }
  }

  public boolean isFirstCapital(String str) {
    char ch = str.charAt(0);
    String letter = Character.toString(ch);
    return letter.equals(letter.toUpperCase());
  }

  public void runSocketThread() {
    SocketThread socketThread = getSocketThread();
    socketThread.setDaemon(true);
    socketThread.start();
    try {
      synchronized (this) {
        wait();
      }

    } catch (InterruptedException e) {
      System.out.println("Thread was interrupted");
      System.exit(1);
    }

    if (clientConnected) {
      System.out.println("Connected");
      while (clientConnected) {
        String usermessege = ScreenShower.readString();
        sendTextMessage(usermessege);
      }
    } else {
      System.out.println("Some error occured");
    }
  }

  public class SocketThread extends Thread {

    public void run() {

      try {
        Socket socket = null;
        socket = new Socket(getServerAddress(), getServerPort());
        connection = new Connection(socket);
        clientCon();
        clientLoop();
      } catch (IOException | ClassNotFoundException e) {

        notifyConnectionStatusChanged(false);
      }
    }

    protected void processIncomingMessage(String message) {
      ScreenShower.writeMessage(message);
    }

    protected void informAboutAddingNewUser(String userName) {
      ScreenShower.writeMessage(userName + " has joined the chat.");
    }

    protected void notifyConnectionStatusChanged(boolean clientConnected) {
      synchronized (Client.this) {
        Client.this.clientConnected = clientConnected;
        Client.this.notify();
      }
    }

    protected void clientCon() throws IOException {
      Message m = null;

      while (!clientConnected) {
        try {
          m = connection.receive();
        } catch (ClassNotFoundException e) {
          throw new IOException("Unexpected MessageType");
        }
        if (m.getType() == MessageType.NAME_REQUEST) {
          connection.send(new Message(MessageType.USER_NAME, getUserName()));
        } else {
          if (m.getType() == MessageType.NAME_ACCEPTED) {
            notifyConnectionStatusChanged(true);
          } else {
            throw new IOException("Unexpected MessageType");
          }
        }
      }
    }

    protected void clientLoop() throws IOException, ClassNotFoundException {
      Message m = null;
      while (true) {
        m = connection.receive();

        if (m.getType() == MessageType.TEXT) {
          processIncomingMessage(m.getData());
        } else if (m.getType() == MessageType.USER_ADDED) {
          informAboutAddingNewUser(m.getData());
        } else {
          throw new IOException("Unexpected MessageType");
        }
      }
    }
  }

  protected String getServerAddress() {
    // System.out.print("Input an IP: ");
    // String ipAddress= ScreenShower.readString();
    String lhost = "localhost";
    return lhost;
  }

  protected int getServerPort() {
    /*System.out.print("Input a server port: ");
    int serverPort = ScreenShower.readInt();
    return serverPort;
    */
    return 2020;
  }

  protected String getUserName() {
    /*System.out.print("Input your name: ");
    String username = ScreenShower.readString();
    while (!isFirstCapital(username)) {
      System.out.print("Your name has to start from a capital letter! ");
      username = ScreenShower.readString();
    }
    return username;
    */
    return "__";
  }

  protected boolean shouldSendTextFromConsole() {
    return true;
  }

  protected SocketThread getSocketThread() {
    SocketThread newThread = new SocketThread();

    return newThread;
  }

  public void run() {
    SocketThread socketThread = getSocketThread();
    socketThread.setDaemon(true);
    socketThread.start();

    try {
      synchronized (this) {
        wait();
      }

    } catch (InterruptedException e) {
      System.out.println("Thread was interrupted");
      System.exit(1);
    }

    if (clientConnected) {
      System.out.println("Соединение установлено. Для выхода наберите команду 'exit'.");
      while (clientConnected) {
        String usermessege = ScreenShower.readString();
        if (usermessege == "exit") {
          break;
        } else {
          if (shouldSendTextFromConsole()) sendTextMessage(usermessege);
        }
      }
    } else {
      System.out.println("Произошла ошибка во время работы клиента.");
    }
  }

  protected void sendTextMessage(String text) {
    try {
      connection.send(new Message(MessageType.TEXT, text));

    } catch (IOException e) {
      System.out.println("Can not be sended");
      clientConnected = false;
    }
  }
}
