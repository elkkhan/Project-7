package tos.chat.Client;

// import com.sun.deploy.uitoolkit.ui.ScreenShower;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import tos.chat.ScreenShower;

public class ChatBot extends Client {

  public static void main(String[] args) {
    ChatBot bClient = new ChatBot();

    bClient.runSocketThread();
  }

  public class BotSocketThread extends SocketThread {
    protected void clientMainLoop() throws IOException, ClassNotFoundException {
      String hello =
          "Hello World! :) If you don't know what to cook today, just ask me! Type one word such as: meat, pizza, pasta or soup";
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
              case "meat":
                dish = "Lets see how to cook " + meat_random();
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
  public String meat_random(){
    HashMap<Integer,String> meat_dish = new HashMap<Integer, String>();
    meat_dish.put(1, "Curry chicken");
    meat_dish.put(2, "Boiled beef");
    meat_dish.put(3, "Fried pork");
    meat_dish.put(4, "Steak");
    meat_dish.put(5, "BBQ ribs");
    meat_dish.put(6, "Chicken wings");
    meat_dish.put(7, "BBQ ribs");

    Random random = new Random();
    int rnd_meat = random.nextInt(5);
    String dish = meat_dish.get(rnd_meat);
    return dish;
  }
}
