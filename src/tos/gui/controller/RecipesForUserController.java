package tos.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import tos.common.api.entities.Ingredient;
import tos.common.api.entities.Recipe;


public class RecipesForUserController implements Initializable {

  @FXML
  private TableColumn<Recipe, Double> receipeWeightColumn;

  @FXML
  private TextField ingredientsField;

  @FXML
  private TextField timeField;

  @FXML
  private TextField nameField;

  @FXML
  private ImageView imageView;

  @FXML
  private TextField brandnamefield11;

  @FXML
  private TableColumn<Recipe, Double> receipeTimeColumn;

  @FXML
  private TableView<Recipe> tableViewRecipes;

  @FXML
  private TableColumn<Recipe, String> receipeNameColumn;

  @FXML
  private TableColumn<Recipe, Double> receipeYieldColumn;

  @FXML
  private TextField brandnamefield111;

  @FXML
  private TableColumn<Recipe, Double> receipeCaloriesColumn;

  @FXML
  private TextField caloriesField;

  @FXML
  private Label name;

  @FXML
  private TextField weightField;

  @FXML
  private TableColumn<Ingredient, String> receipeIngredientsColumn;

  @FXML
  private TextField yieldField;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //setColumnContent();
  }
  /*
  public void setColumnContent() {

    receipeNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Recipe, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Recipe, String> data) {
						return ObjectConstant.<String>valueOf(data.getValue().getLabel());
					}

				});

    receipeYieldColumn.setCellValueFactory(new Callback<CellDataFeatures<Receipt, Double>, ObservableValue<Double>>() {

      @Override
      public ObservableValue<Double> call(CellDataFeatures<Recipe, Double> data) {
        return ObjectConstant.valueOf(data.getValue().getYield());
      }

    });

  }
*/


}
