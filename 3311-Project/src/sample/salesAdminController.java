package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class salesAdminController implements Initializable {

    @FXML
    private Button sBack;
    @FXML
    private Label sUser, sStore;
    @FXML
    private TableView<tableView> sTable;
    @FXML
    private TableColumn<tableView,String> sTName, sTPrice, sTIsle;
    ObservableList<tableView> oblist = FXCollections.observableArrayList();
    
    public void getUser(String user){
        sUser.setText(user);
    }
    public void getStore(String store){
        sStore.setText(store);
        table(sStore.getText());
    }
    
    public void backB(ActionEvent event){
        Stage stage = (Stage) sBack.getScene().getWindow();
        stage.close();
        back_Button();
    }
    
    public void back_Button(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
            Parent root = loader.load();
            loginAdminController loginController = loader.getController();
            loginController.getUser(sUser.getText());
            Stage loggedinStage = new Stage();
            loggedinStage.initStyle(StageStyle.UNDECORATED);
            loggedinStage.setScene(new Scene(root, 600, 400));
            loggedinStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void table(String store){
        sStore.setText(store);
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM items WHERE sale = 'TRUE' AND store = '" + sStore.getText() + "'");

            while(rs.next()){
                oblist.add(new tableView(rs.getString("name"),rs.getString("price"), rs.getString("isle")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sTName.setCellValueFactory(new PropertyValueFactory<tableView,String>("name"));
        sTPrice.setCellValueFactory(new PropertyValueFactory<tableView,String>("price"));
        sTIsle.setCellValueFactory(new PropertyValueFactory<tableView,String>("isle"));

        sTable.setItems(oblist);
    }
}


