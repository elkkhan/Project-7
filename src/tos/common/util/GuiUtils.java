package tos.common.util;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GuiUtils {

  private GuiUtils() {}

  public static void showMessage(String title, String contentText) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(contentText);
    alert.showAndWait();
  }

  public static URL getResource(Class classInstance, String resourceName) {
    ClassLoader classLoader = classInstance.getClassLoader();
    return classLoader.getResource(resourceName);
  }

  public static void closeWindow(Stage stage) {
    stage.close();
  }

  public static void openWindow(URL fxml, URL css, String windowTitle) {
    Parent root;
    try {
      root = FXMLLoader.load(fxml);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    Stage stage = new Stage();
    stage.setTitle(windowTitle);
    Scene scene = new Scene(root);
    if (css != null) {
      scene.getStylesheets().add(css.toExternalForm());
    }
    stage.setScene(scene);
    stage.show();
  }

  public static Connection getConnection() {
    Connection con = null;
    try {
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://db4free.net:3306/tosdatabase";
      String username = "eltetools";
      String password = "123456789";
      //Class.forName(driver);
      con = DriverManager.getConnection(url, username, password);

      System.out.println("connected");


    } catch (Exception e) {
      e.getStackTrace();
    }
    return con;
  }
}

