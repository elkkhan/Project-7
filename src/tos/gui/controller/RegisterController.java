package tos.gui.controller;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController implements Initializable {


  @FXML
  private TextField name;

  @FXML
  private TextField surname;

  @FXML
  private DatePicker datePicker;

  @FXML
  private PasswordField password;
  @FXML
  private TextField id;
  @FXML
  private TextField email;


  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }

  // Event Listener on Button.onAction
  @FXML  // I will fix this register.
  public void register(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {

    if (name.getText().matches("\\d*") || surname.getText().matches("\\d*") || id.getText()
        .equals("") || email.getText().equals("")) {
      //showMessage("Error", "You have empty fields.");
    } else {

      System.out.println("THIS PART WILL ADD USERS INTO DATABASE.");


    }
  }

	/*private void closeRegister() {
		((Stage) id.getScene().getWindow()).close();
	}*/

	/*@FXML
	public void BackFromRegister(ActionEvent event)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		loadPage("../view/Login.fxml");
		closeRegister();

	}*/

}
