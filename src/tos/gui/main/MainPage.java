package tos.gui.main;

import java.io.File;
import java.net.MalformedURLException;
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

    try {
      URL mainFxml = getResourcePath("src", "tos", "gui", "view", "MainPage.fxml");
      URL mainCss = getResourcePath("src", "tos", "gui", "view", "newstyle.css");
      TimeZone.setDefault(TimeZone.getTimeZone("Europe/Budapest"));
      AnchorPane root = FXMLLoader.load(mainFxml);
      Scene scene = new Scene(root);
      scene.getStylesheets().add(mainCss.toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setResizable(true);
      primaryStage.setTitle("Recipe");
      primaryStage.show();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  private URL getResourcePath(String... path) throws MalformedURLException {
    File file = new File(path[0]);

    for (int i = 1; i < path.length; i++) {
      file = new File(file, path[i]);
    }
    return file.toURI().toURL();
  }
}
