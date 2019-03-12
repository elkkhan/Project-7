package tos.gui.main;

import java.io.IOException;
import java.util.TimeZone;
import tos.common.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tos.common.api.entities.Recipe;

public class MainPage extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
            TimeZone.setDefault(TimeZone.getTimeZone("Europe/Budapest"));

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/MainPage.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../view/newstyle.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.setTitle("Receip");
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    /*
	 public static void main(String[] args) {
	        launch(args);
	        
	    }

	    */
	
	
}
