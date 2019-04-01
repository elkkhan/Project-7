package tos.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import tos.common.util.GuiUtils;

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

  @FXML
  public void login_action(ActionEvent event) throws Exception {
    URL fxml = GuiUtils.getResource(getClass(), "Login.fxml");
    GuiUtils.openWindow(fxml, null, "Login");
  }

  @FXML
  public void register_action(ActionEvent event) throws Exception {
    URL url1 = new File("src\\tos\\gui\\view\\Register.fxml").toURL();
    //loadPage(url1.toExternalForm());

  }

  @FXML
  public void enter_action(ActionEvent event) throws Exception {
    URL url1 = new File("src\\tos\\gui\\view\\Login.fxml").toURL();
    //loadPage(url1.toExternalForm());
  }


}
