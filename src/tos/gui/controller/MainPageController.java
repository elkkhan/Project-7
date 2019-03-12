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

    private URL getResourcePath(String... path) throws MalformedURLException {
        File file = new File(path[0]);

        for (int i = 1; i < path.length; i++) {
            file = new File(file, path[i]);
        }
        return file.toURI().toURL();
    }

    private void loadPage(String source) throws MalformedURLException {
        URL maincss = getResourcePath("src", "tos", "gui", "view", "newstyle.css");
        try {
            Pane root = (Pane) FXMLLoader.load(getClass().getResource(source));
            // Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(maincss.toExternalForm());
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
        URL maincss = getResourcePath("src", "tos", "gui", "view", "Login.fxml");
        System.out.println(maincss.toExternalForm());
        //loadPage(maincss.toExternalForm());
    }

    @FXML
    public void register_action(ActionEvent event) throws Exception {
        URL maincss = getResourcePath("src", "tos", "gui", "view", "Register.fxml");
        loadPage(maincss.toExternalForm());

    }

    @FXML
    public void enter_action(ActionEvent event) throws Exception {
        URL maincss = getResourcePath("src", "tos", "gui", "view", "Login.fxml");
        loadPage(maincss.toExternalForm());
    }


}
