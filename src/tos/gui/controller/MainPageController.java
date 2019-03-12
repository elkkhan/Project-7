package tos.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @FXML
    public void login_action(ActionEvent event) throws Exception {
        URL url1 = new File("src\\tos\\gui\\view\\Login.fxml").toURL();
        System.out.println(url1.toExternalForm());
        loadPage(url1.toExternalForm());
    }

    @FXML
    public void register_action(ActionEvent event) throws Exception {
        URL url1 = new File("src\\tos\\gui\\view\\Register.fxml").toURL();
        loadPage(url1.toExternalForm());

    }

    @FXML
    public void enter_action(ActionEvent event) throws Exception {
        URL url1 = new File("src\\tos\\gui\\view\\Login.fxml").toURL();
        loadPage(url1.toExternalForm());
    }


}
