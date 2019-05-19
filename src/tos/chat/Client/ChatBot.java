package tos.chat.Client;

// import com.sun.deploy.uitoolkit.ui.ScreenShower;
import java.io.IOException;
import java.util.HashMap;
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
              case "pizza":
                dish = "Lets see how to cook " + pizza_random() + " pizza";
                break;
              case "pasta":
                dish = "Lets see how to cook pasta " + pasta_random();
                break;
              case "soup":
                dish = "Lets see how to cook soup " + soup_random() + " soup";
                break;
              case "salat":
                dish = "Lets see how to cook salad " + salad_random() + " salad";
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

  public String meat_random() {
    HashMap<Integer, String> meat_dish = new HashMap<Integer, String>();
    meat_dish.put(1, "Curry chicken");
    meat_dish.put(2, "Boiled beef");
    meat_dish.put(3, "Fried pork");
    meat_dish.put(4, "Steak");
    meat_dish.put(5, "BBQ ribs");
    meat_dish.put(6, "Chicken wings");
    meat_dish.put(7, "BBQ ribs");

    Random random = new Random();
    int rnd_meat = random.nextInt(7);

    String dish = meat_dish.get(rnd_meat);
    return dish;
  }

  public String pizza_random() {
    HashMap<Integer, String> pizza = new HashMap<Integer, String>();
    pizza.put(1, "Margarita");
    pizza.put(2, "Hawaii");
    pizza.put(3, "4 Cheeses");
    pizza.put(4, "Vegeterian");
    pizza.put(5, "BBQ ");
    pizza.put(6, "Salami");

    Random random = new Random();
    int rnd_pizza = random.nextInt(6);

    String dish = pizza.get(rnd_pizza);
    return dish;
  }

  public String pasta_random() {
    HashMap<Integer, String> pasta = new HashMap<Integer, String>();
    pasta.put(1, "Carbonara");
    pasta.put(2, "Po flotski");
    pasta.put(3, "Bolognese");

    Random random = new Random();
    int rnd_pasta = random.nextInt(3);

    String dish = pasta.get(rnd_pasta);
    return dish;
  }

  public String soup_random() {
    HashMap<Integer, String> soup = new HashMap<Integer, String>();
    soup.put(1, "Tom Yum");
    soup.put(2, "Borsch");
    soup.put(3, "Cream soup");
    soup.put(4, "Guliash");

    Random random = new Random();
    int rnd_soup = random.nextInt(4);

    String dish = soup.get(rnd_soup);
    return dish;
  }

  public String salad_random() {
    HashMap<Integer, String> salad = new HashMap<Integer, String>();
    salad.put(1, "Ceasar");
    salad.put(2, "Vegeterian");
    salad.put(3, "Caprese");

    Random random = new Random();
    int rnd_salad = random.nextInt(3);

    String dish = salad.get(rnd_salad);
    return dish;
  }
}
