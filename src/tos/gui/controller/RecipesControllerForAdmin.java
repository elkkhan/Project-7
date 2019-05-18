package tos.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tos.common.api.entities.Ingredient;
import tos.common.api.entities.Recipe;
import tos.common.util.GuiUtils;

public class RecipesControllerForAdmin implements Initializable {

  @FXML private Label name;
  @FXML
  private Button add;

  @FXML
  private TextField ingredientsField;

  @FXML
  private TableColumn<Recipe, Ingredient> ingredientscolumn;

  @FXML
  private TableColumn<Recipe, Double> yieldcolumn;

  @FXML
  private TextField timeField;

  @FXML
  private TextField nameField;

  @FXML
  private Button back;


  @FXML
  private TableColumn<Recipe, Double> weightcolumn;

  @FXML
  private TableView<Recipe> tableViewRecipes;

  @FXML
  private TableColumn<Recipe, Double> timecolumn;

  @FXML
  private TableColumn<Recipe, String> namecolumn;

  @FXML
  private TableColumn<Recipe, Double> caloriescolumn;

  @FXML
  private Button logout;

  @FXML
  private TextField caloriesField;

  @FXML
  private TextField pricefield;

  @FXML
  private TableColumn<Recipe, String> labelcolumn;
  @FXML
  private TextField yieldField;

  @FXML
  private Button export;


  @Override
  public void initialize(URL location, ResourceBundle resources) {

    // tableViewRecipes.setItems(FXCollections.observableArrayList(listAllRecipe()));

  }

  private List<Recipe> listAllRecipe() {

    return null;
  }





  private void close() {
    ((Stage) name.getScene().getWindow()).close();
  }


  @FXML
  public void back(ActionEvent event) {
    URL fxml = GuiUtils.getResource(getClass(), "MainPage.fxml");
    GuiUtils.openWindow(fxml, null, "Main Page");
    close();
  }

  @FXML
  public void logout(ActionEvent event) {
    close();
  }

  @FXML
  public void add(ActionEvent event) {

    close();
  }
}
