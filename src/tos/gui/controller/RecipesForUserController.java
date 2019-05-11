package tos.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import tos.chat.Client.ClientGuiController;
import tos.chat.Server;
import tos.common.api.client.ApiClient;
import tos.common.api.entities.Recipe;
import tos.common.api.exceptions.ConnectionException;
import tos.common.api.exceptions.ParserException;
import tos.common.api.exceptions.QueryBuilderException;
import tos.common.api.query.ApiQuery;
import tos.common.util.GuiUtils;

public class RecipesForUserController implements Initializable {

  @FXML private TextField yield;

  @FXML private TextField calories;

  @FXML private TextField label;

  @FXML private ImageView imageView;

  @FXML private TableView<Recipe> tableViewRecipes;

  @FXML private TableColumn<Recipe, String> LabelColumn;

  @FXML private TableColumn<Recipe, Double> YieldColumn;

  @FXML private TableColumn<Recipe, Double> CaloriesColumn;

  @FXML private Label name;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    YieldColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Double>("Yield"));
    LabelColumn.setCellValueFactory(new PropertyValueFactory<Recipe, String>("Label"));
    CaloriesColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Double>("Calories"));
    try {
      tableViewRecipes.setItems(ListRecipes());
    } catch (ConnectionException e) {
      e.printStackTrace();
    } catch (ParserException e) {
      e.printStackTrace();
    } catch (QueryBuilderException e) {
      e.printStackTrace();
    }
  }

  public ObservableList<Recipe> ListRecipes()
      throws ConnectionException, ParserException, QueryBuilderException {
    ApiClient apiClient = new ApiClient();
    ApiQuery query = apiClient.createQuery("Recipe").build();
    List<Recipe> chickenPizza = apiClient.executeQuery(query);
    System.out.println(chickenPizza.size());
    // test-dsa
    ObservableList<Recipe> observableList = FXCollections.observableList(chickenPizza);
    return observableList;
  }

  @FXML // I will fix this register.
  public void add(ActionEvent event)
      throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {

    if (calories.getText().equals("") || yield.getText().equals("") || label.getText().equals("")) {
      GuiUtils.showMessage("Error", "You have empty fields.");
    } else {
      String llabel = label.getText();
      String yyield = yield.getText();
      String ccalories = calories.getText();

      PreparedStatement ps;
      String query;
      query = "INSERT INTO `Recipes`(`YIELD`, `LABEL`, `CALORIES`) VALUES (?,?,?)";
      try {
        ps = GuiUtils.getConnection().prepareStatement(query);
        ps.setString(1, llabel);
        ps.setString(2, yyield);
        ps.setString(3, ccalories);
        if (ps.executeUpdate() > 0) {
          GuiUtils.showMessage("Done", "done");
        }
      } catch (Exception r) {
        System.out.println(r);
      }
    }
  }

  @FXML
  public void chat(ActionEvent event) {
    Server sv = new Server();
    ClientGuiController cgc = new ClientGuiController();

    try {
      sv.main(new String[] {});
      cgc.main(new String[] {});
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
