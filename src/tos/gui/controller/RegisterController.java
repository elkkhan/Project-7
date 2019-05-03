package tos.gui.controller;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tos.common.util.GuiUtils;

public class RegisterController implements Initializable {

  String nameT;
  @FXML private TextField name;
  @FXML private TextField surname;
  @FXML private DatePicker datePicker;
  @FXML private PasswordField password;
  @FXML private TextField id;
  @FXML private TextField email;

  public static Connection getConnection() {
    Connection con = null;
    try {
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://db4free.net:3306/tosdatabase";
      String username = "eltetools";
      String password = "123456789";
      // Class.forName(c);
      Connection conn = DriverManager.getConnection(url, username, password);
      System.out.println("connected");


    } catch (Exception e) {
      e.getStackTrace();
    }
    return con;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    //datePicker.setConverter(new DateConverter());
  }

  public boolean isCorrectForRegister(
      String nameT, String surnameT, String idT, String emailT, String passwordT) {
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
    return !nameT.equals("\\d+")
        && nameT.length() >= 2
        && surnameT.length() >= 2
        && !surnameT.equals("\\d+")
        && passwordT.length() >= 5
        && emailT.length() >= 5;
  }

  // Event Listener on Button.onAction
  @FXML // I will fix this register.
  public void register(ActionEvent event)
      throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {

    if (name.getText().matches("\\d*")
        || surname.getText().matches("\\d*")
        || id.getText().equals("")
        || email.getText().equals("")) {
      GuiUtils.showMessage("Error", "You have empty fields.");
    } else {
      String username = name.getText();
      String ssurname = surname.getText();
      String idd = id.getText();
      String eemail = email.getText();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      //String bd = df.format(datePicker.getConverter().toString());
      byte[] ppassword = password.getText().getBytes();

      PreparedStatement ps;
      String query = "INSERT INTO `Users`(`NAME`, `SURNAME`, `ID`, `AGE`, `E-MAIL`, `PASSWORD`) VALUES (?,?,?,?,?,?,?)";
      // try {
      ps = getConnection().prepareStatement(query);
      ps.setString(1, username);
      ps.setString(2, ssurname);
      ps.setString(3, idd);
      ps.setString(4, "11-11-2019");
      ps.setString(5, eemail);
      ps.setBytes(6, ppassword);
      if (ps.executeUpdate() > 0) {
        GuiUtils.showMessage("Done", "done");
      }
      // } catch (Exception r) {
      //  System.out.println(r);
      //}
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
