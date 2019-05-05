package tos.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tos.common.util.GuiUtils;

public class AskTypeController implements Initializable {

  @FXML
  private Label name;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  @FXML
  public void back(ActionEvent event) {
    URL fxml = GuiUtils.getResource(getClass(), "MainPage.fxml");
    GuiUtils.openWindow(fxml, null, "Main Page");
    close();
  }

  @FXML
  public void admin(ActionEvent event) {
    URL fxml = GuiUtils.getResource(getClass(), "LoginForAdmin.fxml");
    GuiUtils.openWindow(fxml, null, "Main Page");
    close();
  }

  @FXML
  public void user(ActionEvent event) {
    URL fxml = GuiUtils.getResource(getClass(), "LoginForUser.fxml");
    GuiUtils.openWindow(fxml, null, "Main Page");
    close();
  }

  private void close() {
    ((Stage) name.getScene().getWindow()).close();
  }


}
