package tos.chat.Client;

// import com.sun.deploy.uitoolkit.ui.ScreenShower;
import java.io.IOException;
import tos.chat.ScreenShower;

public class ChatBot extends Client {

  public static void main(String[] args) {
    ChatBot bClient = new ChatBot();

    bClient.run();
  }

  public class BotSocketThread extends SocketThread {
    protected void clientMainLoop() throws IOException, ClassNotFoundException {
      String hello =
          "Hello World! :) If you don't know what to cook today, just ask me! Type one word such as: pork, chicken, beef, pizza, pasta or soup";
      sendTextMessage(hello);
      super.clientCon();
    }

    @Override
    protected void processIncomingMessage(String message) {
      if (message != null) {
        ScreenShower.writeMessage(message);
        String dish = null;
        if (message.contains(": ")) {
          String[] massiv = message.split(": ");
          if (massiv.length == 2 && massiv[1] != null) {
            String name = massiv[0];
            String text = massiv[1];
            switch (text) {
              case "pork":
                // format = new SimpleDateFormat("d.MM.YYYY");
                break;
              case "chicken":
                dish = "Lets see how to cook chicken";
                break;
              case "beef":
                dish = "Lets see how to cook beef";
                break;
              case "pizza":
                dish = "Lets see how to cook pizza";
                break;
              case "pasta":
                dish = "Lets see how to cook pasta";
                break;
              case "soup":
                dish = "Lets see how to cook soup";
                break;
              case "salat":
                dish = "Lets see how to cook salat";
                break;
              case "lamb":
                dish = "Lets see how to cook lamb";
                break;
            }
            if (dish != null) {
              sendTextMessage(String.format("Information  %s: %s", name, dish));
            }
          }
        }
      }
    }
  }

  protected SocketThread getSocketThread() {
    BotSocketThread botSocketThread = new BotSocketThread();
    return botSocketThread;
  }

  protected boolean shouldSendTextFromConsole() {
    return false;
  }

  protected String getUserName() {
    String name = "dish_bot_" + ((int) (Math.random() * 100));

    return name;
  }
}
