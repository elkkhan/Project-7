package tos.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tos.common.util.GuiUtils;

public class AdminController implements Initializable {

  @FXML private Label name;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  private void close() {
    ((Stage) name.getScene().getWindow()).close();
  }

  public void checkRecipes(ActionEvent event) throws Exception {
    URL fxml = GuiUtils.getResource(getClass(), "CheckRecipesForAdmin.fxml");
    GuiUtils.openWindow(fxml, null, null);
    close();
  }

  public void checkUsers(ActionEvent event) throws Exception {
    URL fxml = GuiUtils.getResource(getClass(), "CheckUsers.fxml");
    GuiUtils.openWindow(fxml, null, null);
    close();
  }

  @FXML
  public void logout(ActionEvent event) {
    close();
  }
}
