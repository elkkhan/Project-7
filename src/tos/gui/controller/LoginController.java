package tos.gui.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

  //Preferences pref; we will need this.

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }

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

  private void loadPage(String source) throws MalformedURLException {
    URL url1 = new File("src\\tos\\gui\\view\\newstyle.css").toURL();
    try {
      Pane root = (Pane) FXMLLoader.load(getClass().getResource(source));
      // Parent root = loader.load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(url1.toExternalForm());
      Stage primaryStage = new Stage();
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.setTitle("Big Store");
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

/*	@FXML
	public void registerPage(ActionEvent event) {
		loadPage("../view/Register.fxml");
		closeLogin();
	}*/


}
