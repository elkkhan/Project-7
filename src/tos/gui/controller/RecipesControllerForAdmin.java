package tos.gui.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tos.common.api.entities.Ingredient;
import tos.common.util.GuiUtils;
import tos.gui.model.RecipesModel;

public class RecipesControllerForAdmin implements Initializable {

  @FXML private Label name;
  @FXML private Button add;

  @FXML private TextField ingredientsField;

  ObservableList<RecipesModel> oblist = FXCollections.observableArrayList();
  @FXML private TableColumn<RecipesModel, Ingredient> ingredientscolumn;

  @FXML private TextField timeField;

  @FXML private TextField nameField;
  @FXML private TableColumn<RecipesModel, Double> yieldcolumn;

  @FXML private Button back;
  @FXML private TextField labelfield;
  @FXML private TableColumn<RecipesModel, Double> weightcolumn;
  @FXML private TableView<RecipesModel> tableViewRecipes;
  @FXML private TableColumn<RecipesModel, Double> timecolumn;
  @FXML private TableColumn<RecipesModel, String> namecolumn;

  @FXML private Button logout;

  @FXML private TextField caloriesField;
  @FXML private TableColumn<RecipesModel, Double> caloriescolumn;
  @FXML private TextField yieldField;
  @FXML private TableColumn<RecipesModel, String> labelcolumn;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Connection con = GuiUtils.getConnection();
    try {
      ResultSet rs = con.createStatement().executeQuery("select * from Recipes");
      while (rs.next()) {
        oblist.add(
            new RecipesModel(
                rs.getString("Yield"), rs.getString("Label"), rs.getDouble("Calories")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    yieldcolumn.setCellValueFactory(new PropertyValueFactory<>("Yield"));
    labelcolumn.setCellValueFactory(new PropertyValueFactory<>("Label"));
    caloriescolumn.setCellValueFactory(new PropertyValueFactory<>("Calories"));
    tableViewRecipes.setItems(oblist);
  }

  private void refreshTable() {
    try {
      PreparedStatement ps;
      Connection con = GuiUtils.getConnection();
      ResultSet rs;
      String sql = "select * from Recipes";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      tableViewRecipes.setItems(oblist);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void close() {
    ((Stage) name.getScene().getWindow()).close();
  }

  @FXML
  public void back(ActionEvent event) {
    URL fxml = GuiUtils.getResource(getClass(), "Admin.fxml");
    GuiUtils.openWindow(fxml, null, "Admin");
    close();
  }

  @FXML
  public void logout(ActionEvent event) {
    close();
  }

  @FXML
  public void add(ActionEvent event) {

    if (yieldField.getText().matches("")
        || caloriesField.getText().matches("")
        || labelfield.getText().equals("")) {
      GuiUtils.showMessage("Error", "You have empty fields.");
    } else {
      String yield = yieldField.getText();
      String fieldo = labelfield.getText();
      String calo = caloriesField.getText();

      PreparedStatement ps;
      String query;
      query = "INSERT INTO `Recipes`(`YIELD`, `LABEL`, `CALORIES`) VALUES (?,?,?)";
      try {
        ps = GuiUtils.getConnection().prepareStatement(query);
        ps.setString(1, yield);
        ps.setString(2, fieldo);
        ps.setInt(3, Integer.parseInt(calo));

        if (ps.executeUpdate() > 0) {
          GuiUtils.showMessage("Done", "done");
        }
      } catch (Exception r) {
        System.out.println(r);
      }
    }
  }

  @FXML
  private void delete(ActionEvent event) {
    tableViewRecipes.getItems().removeAll(tableViewRecipes.getSelectionModel().getSelectedItem());
  }
}
