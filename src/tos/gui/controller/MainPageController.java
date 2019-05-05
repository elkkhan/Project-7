package tos.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tos.common.api.client.ApiClient;
import tos.common.api.entities.Ingredient;
import tos.common.api.entities.NutrientInfo;
import tos.common.api.entities.Recipe;
import tos.common.api.exceptions.ConnectionException;
import tos.common.api.exceptions.ParserException;
import tos.common.api.exceptions.QueryBuilderException;
import tos.common.api.query.ApiQuery;
import tos.common.util.GuiUtils;

public class MainPageController implements Initializable {

  @FXML
  private TableView<Recipe> mainTable;

  @FXML
  private TableColumn<Recipe, Double> YieldColumn;

  @FXML
  private TableColumn<Recipe, Double> TimeColumn;

  @FXML
  private TableColumn<Recipe, Double> CaloriesColumn;

  @FXML
  private Label name;

  @FXML
  private Button enter;

  @FXML
  private TableColumn<Recipe, NutrientInfo[]> NutrientsColumn;

  @FXML
  private Button login;

  @FXML
  private TableColumn<Recipe, String> LabelColumn;

  @FXML
  private TableColumn<Recipe, Double> WeightColumn;

  @FXML
  private Button register;

  @FXML
  private TableColumn<Recipe, Ingredient[]> IngredientColumn;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    YieldColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Double>("Yield"));
    LabelColumn.setCellValueFactory(new PropertyValueFactory<Recipe, String>("Label"));
    NutrientsColumn
        .setCellValueFactory(new PropertyValueFactory<Recipe, NutrientInfo[]>("NutrientInfo"));
    CaloriesColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Double>("Calories"));
    WeightColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Double>("Weight"));
    TimeColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Double>("Time"));
    IngredientColumn
        .setCellValueFactory(new PropertyValueFactory<Recipe, Ingredient[]>("Ingredient"));

    try {
      mainTable.setItems(ListRecipes());
    } catch (ConnectionException e) {
      e.printStackTrace();
    } catch (ParserException e) {
      e.printStackTrace();
    } catch (QueryBuilderException e) {
      e.printStackTrace();
    }

  }

  @FXML
  public void login_action(ActionEvent event) throws Exception {
    URL fxml = GuiUtils.getResource(getClass(), "AskType.fxml");
    GuiUtils.openWindow(fxml, null, "Login");
    close();
  }

  private void close() {
    ((Stage) name.getScene().getWindow()).close();
  }

  @FXML
  public void register_action(ActionEvent event) throws Exception {
    URL fxml = GuiUtils.getResource(getClass(), "Register.fxml");
    GuiUtils.openWindow(fxml, null, "Register");
    close();
  }

  @FXML
  public void enter_action(ActionEvent event) throws Exception {
    URL fxml = GuiUtils.getResource(getClass(), "CheckRecipesForAdmin.fxml");
    GuiUtils.openWindow(fxml, null, "Recipes");
    close();

  }

  public ObservableList<Recipe> ListRecipes()
      throws ConnectionException, ParserException, QueryBuilderException {
    ApiClient apiClient = new ApiClient();
    ApiQuery query = apiClient.createQuery("yields").build();
    List<Recipe> chickenPizza = apiClient.executeQuery(query);
    System.out.println(chickenPizza.size());

    ObservableList<Recipe> observableList = FXCollections.observableList(chickenPizza);
    return observableList;

  }

}
