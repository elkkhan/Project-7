package tos.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tos.common.util.GuiUtils;

public class MainPageController implements Initializable {

  @FXML private Button register;
  @FXML private Button enter;
  @FXML private Button login;
  @FXML
  private Label name;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  @FXML
  public void login_action(ActionEvent event) throws Exception {
    URL fxml = GuiUtils.getResource(getClass(), "AskType.fxml");
    GuiUtils.openWindow(fxml, null, "Login");
    close();
  }

  private void close() {
    ((Stage) name.getScene().getWindow()).close();
  }

  @FXML
  public void register_action(ActionEvent event) throws Exception {
    URL fxml = GuiUtils.getResource(getClass(), "Register.fxml");
    GuiUtils.openWindow(fxml, null, "Register");
    close();
  }

  @FXML
  public void enter_action(ActionEvent event) throws Exception {
    URL fxml = GuiUtils.getResource(getClass(), "CheckRecipes.fxml");
    GuiUtils.openWindow(fxml, null, "Recipes");
    close();

  }
}
