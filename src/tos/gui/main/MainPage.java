package tos.gui.main;

import java.io.File;
import java.net.URL;
import java.util.TimeZone;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainPage extends Application {

  public static void main(String[] args) {
    launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // TODO Auto-generated method stub
    URL url1 = new File("src\\tos\\gui\\view\\MainPage.fxml").toURL();
    URL url2 = new File("src\\tos\\gui\\view\\newstyle.css").toURL();
    try {

      TimeZone.setDefault(TimeZone.getTimeZone("Europe/Budapest"));

      AnchorPane root = FXMLLoader.load(url1);
      Scene scene = new Scene(root);
      scene.getStylesheets().add(url2.toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setResizable(true);
      primaryStage.setTitle("Receip");
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
