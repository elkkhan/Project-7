package tos.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

  public Button login;
  AuthenticationManager authm;
  @FXML private TextField name;
  @FXML private TextField surname;
  @FXML private TextField id;
  @FXML private PasswordField password;

  // Preferences pref; we will need this.

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {}

  /*
  @FXML
  public void login(ActionEvent event) throws Exception {

  	try {

  		if(authm.authenticateUser(id.getText(), password.getText().toCharArray())) {
  			pref.put("id", id.getText());  // root will be ID  for each person and preferences will be needed here.
  			loadPage("../view/userPage.fxml");
  			closeLogin();
  			return;
  		}

  	}catch(Exception e) {

  		e.getStackTrace();


  	}

  	try {

  		if(authm.authenticateAdmin(id.getText(), password.getText().toCharArray())) {
  			pref.put("id", id.getText());
  			loadPage("../view/Admin.fxml");
  			closeLogin();
  			return;
  		}

  	}catch(Exception e) {

  		e.getStackTrace();


  	}
  }
  */

  /*	 private void closeLogin() {
  	((Stage)id.getScene().getWindow()).close();
  }*/

  /*	@FXML
  public void registerPage(ActionEvent event) {
  	loadPage("../view/Register.fxml");
  	closeLogin();
  }*/

}
