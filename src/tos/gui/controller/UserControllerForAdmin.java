package tos.gui.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import tos.common.util.GuiUtils;
import tos.gui.model.Users;

public class UserControllerForAdmin implements Initializable {

  ObservableList<Users> oblist = FXCollections.observableArrayList();
  @FXML private TableColumn<Users, String> namecolumn;
  @FXML private Label currentUser;
  @FXML private Button logout;
  @FXML private TableColumn<Users, String> surnamecolumn;
  @FXML private TableView<Users> tableViewUsers;
  @FXML private TableColumn<Users, String> idcolumn;
  @FXML private Button back;
  @FXML private TableColumn<Users, String> emailcolumn;
  @FXML private Button deleteuser;
  @FXML private TableColumn<Users, Date> agecolumn;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Connection con = GuiUtils.getConnection();
    try {
      ResultSet rs = con.createStatement().executeQuery("select * from Users");
      while (rs.next()) {
        oblist.add(
            new Users(
                rs.getString("Name"),
                rs.getString("Surname"),
                rs.getString("ID"),
                rs.getDate("Age"),
                rs.getString("E-Mail"),
                rs.getBytes("Password")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    surnamecolumn.setCellValueFactory(new PropertyValueFactory<>("Surname"));
    idcolumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
    agecolumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
    emailcolumn.setCellValueFactory(new PropertyValueFactory<>("E-Mail"));
    tableViewUsers.setItems(oblist);
  }

  private void close() {
    ((Stage) currentUser.getScene().getWindow()).close();
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
  private void delete(ActionEvent event) {
    tableViewUsers.getItems().removeAll(tableViewUsers.getSelectionModel().getSelectedItem());
  }
}
