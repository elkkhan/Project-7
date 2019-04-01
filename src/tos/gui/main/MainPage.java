package tos.gui.main;

import java.net.URL;
import java.util.TimeZone;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tos.common.util.GuiUtils;

public class MainPage extends Application {

  public static void main(String[] args) {
    launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // TODO Auto-generated method stub
    URL fxml = GuiUtils.getResource(getClass(), "MainPage.fxml");
    URL css = GuiUtils.getResource(getClass(), "newstyle.css");
    try {

      TimeZone.setDefault(TimeZone.getTimeZone("Europe/Budapest"));

      AnchorPane root = FXMLLoader.load(fxml);
      Scene scene = new Scene(root);
      scene.getStylesheets().add(css.toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setResizable(true);
      primaryStage.setTitle("Receipt");
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
