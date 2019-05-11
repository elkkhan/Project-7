package tos.chat.Client;

public class ClientGuiController extends Client {

  public static void main(String[] args) {
    ClientGuiController clientGui = new ClientGuiController();
    clientGui.run();
  }

  private ClientGuiModel model = new ClientGuiModel();
  private GuiView view = new GuiView(this);

  protected SocketThread getSocketThread() {
    GuiSocketThread socketThread = new GuiSocketThread();
    return socketThread;
  }

  public void run() {
    getSocketThread().run();
  }

  public String getServerAddress() {
    return view.getServerAddress();
  }

  public int getServerPort() {
    return view.getServerPort();
  }

  public String getUserName() {
    return view.getUserName();
  }

  public ClientGuiModel getModel() {
    return model;
  }

  public class GuiSocketThread extends SocketThread {

    protected void processIncomingMessage(String message) {
      model.setNewMessage(message);
      view.refreshMessages();
    }

    protected void informAboutAddingNewUser(String userName) {
      model.addUser(userName);
      view.refreshUsers();
    }

    protected void informAboutDeletingNewUser(String userName) {
      model.deleteUser(userName);
      view.refreshUsers();
    }
    /* protected void notifyConnectionStatusChanged(boolean clientConnected){
        view.notifyConnectionStatusChanged(true);
    }
    */
  }
}
