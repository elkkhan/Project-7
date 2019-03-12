package tos.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;

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

        if (name.getText().matches("\\d*") || surname.getText().matches("\\d*") || id.getText().equals("") || email.getText().equals("")) {
                showMessage("Error", "You have empty fields.");
        }
          else {


            System.out.println("THIS PART WILL ADD USERS INTO DATABASE.");


        }
    }

    private void showMessage(String title, String contentText) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


    private void loadPage(String source) {
        try {
            Pane root = (Pane) FXMLLoader.load(getClass().getResource(source));
            // Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../view/style.css").toExternalForm());
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Receip app");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
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
