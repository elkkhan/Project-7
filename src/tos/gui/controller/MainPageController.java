package tos.gui.controller;

import static tos.gui.main.MainPage.getResourcePath;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainPageController implements Initializable {

  @FXML
  private Button register;
  @FXML
  private Button enter;
  @FXML
  private Button login;


  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  private void loadPage(URL source) throws MalformedURLException {
    URL maincss = getResourcePath("src", "tos", "gui", "view", "newstyle.css");
    try {
      Pane root = FXMLLoader.load(source);
      Scene scene = new Scene(root);
      scene.getStylesheets().add(maincss.toExternalForm());
      Stage primaryStage = new Stage();
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.setTitle("Big Store");
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void login_action(ActionEvent event) throws Exception {
    URL maincss = getResourcePath("src", "tos", "gui", "view", "Login.fxml");
    System.out.println(maincss.toExternalForm());
    //loadPage(maincss.toExternalForm());
  }

  @FXML
  public void register_action(ActionEvent event) throws Exception {
    URL maincss = getResourcePath("src", "tos", "gui", "view", "Register.fxml");
    loadPage(maincss);

  }

  @FXML
  public void enter_action(ActionEvent event) throws Exception {
    URL maincss = getResourcePath("src", "tos", "gui", "view", "Login.fxml");
    loadPage(maincss);
  }


}
