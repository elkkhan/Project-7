package tos.gui.controller;
/*
import com.mysql.cj.protocol.Resultset;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.Action;
import tos.common.util.GuiUtils;
*/

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tos.common.util.GuiUtils;

public class LoginController implements Initializable {

  public Button login;
  AuthenticationManager authm;
  @FXML
  private TextField name;
  @FXML
  private TextField surname;
  @FXML
  private TextField id;
  @FXML
  private PasswordField password;
  @FXML
  private Button loginButton;
  @FXML
  private Button registerButton;

  // Preferences pref; we will need this.

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
  }

  //@FXML
//  public void registerButton(Action event) throws Exception {
  //URL fxml = GuiUtils.getResource(getClass(), "Register.fxml");
  //  GuiUtils.openWindow(fxml, null, "Register");
  // }

  @FXML
  public void loginButton(ActionEvent event)
      throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
    PreparedStatement ps;
    ResultSet rs;
    String us = name.getText();
    String pass = String.valueOf(password.getText());
    String query = "SELECT * FROM `Admin` WHERE `NAME`=? AND `PASSWORD`=?";
    try {
      ps = GuiUtils.getConnection().prepareStatement(query);

      ps.setString(1, us);
      ps.setString(2, pass);
      rs = ps.executeQuery();
      if (rs.next()) {
        URL fxml = GuiUtils.getResource(getClass(), "Admin.fxml");
        GuiUtils.openWindow(fxml, null, "Admin");
      } else {
        GuiUtils.showMessage("", "Error");
      }
    } catch (Exception e) {
      System.out.println(e);
    }


  }


  /*	 private void closeLogin() {
  	((Stage)id.getScene().getWindow()).close();
  }*/

  /*	@FXML
  public void registerPage(ActionEvent event) {
  	loadPage("../view/Register.fxml");
  	closeLogin();
  }*/

}

