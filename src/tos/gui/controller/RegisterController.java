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


  String nameT;
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

  public boolean isCorrectForRegister(String nameT, String surnameT, String idT, String emailT,
      String passwordT) {
    /*
   if(nameT==null||surnameT==null){return false;}
   int len = nameT.length();
   for(int i=0; i < len; i++)
   {
     if((Character.isLetter(nameT.charAt(i))==false) || Character.isLetter(surnameT.charAt(i))==false){
       return false;
     }
   }
   return true;
   */
    if (nameT.equals("\\d+") || nameT.length() < 2 || surnameT.length() < 2 || surnameT
        .equals("\\d+") || passwordT.length() < 5 || emailT.length() < 5) {
      return false;
    } else {
      return true;
    }

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
