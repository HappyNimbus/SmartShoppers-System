package sample;

import com.mysql.cj.xdevapi.Table;
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
import java.sql.Statement;
import java.util.ResourceBundle;

public class logInManagerController  implements Initializable {

    @FXML
    private Button lmAdd;
    @FXML
    private Button lmRemove;
    @FXML
    private Button lmUpdate, lmUStore;
    @FXML
    private Button lmLogout;
    @FXML
    private Label lmWelcome;
    @FXML
    private Label lmStore;
    @FXML
    private TableView<tableView> lmTable;
    @FXML
    private TableColumn<tableView,String> lmTName, lmTPrice, lmTIsle;

    ObservableList<tableView> oblist = FXCollections.observableArrayList();


    public String store = null;

    public String welcome(String userName){
        lmWelcome.setText(userName);
        String username = String.valueOf(lmWelcome);
        store();

        return username;
    }

    public void store(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String checkStore = "SELECT storepref FROM users WHERE username = '" + lmWelcome.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(checkStore);

            while(queryResult.next()) {
                store = queryResult.getString("storepref");
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        lmStore.setText(store);
    }
    public void logOutButton(ActionEvent event){
        Stage stage = (Stage) lmLogout.getScene().getWindow();
        stage.close();
        backLogin();
    }

    public void backLogin() {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage backStage = new Stage();
            backStage.initStyle(StageStyle.UNDECORATED);
            backStage.setScene(new Scene(root, 600, 400));
            backStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void addButton(ActionEvent event) {
        Stage stage = (Stage) lmAdd.getScene().getWindow();
        stage.close();
        addItem();
    }

    public void addItem(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addItem.fxml"));
            Parent root = loader.load();
            addItemController controller = loader.getController();
            controller.getUser(lmWelcome.getText());
            controller.getStore(lmStore.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 600, 400));
            infoStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateButton(ActionEvent event){
        Stage stage = (Stage) lmUpdate.getScene().getWindow();
        stage.close();
        updateItem();
    }

    public void updateStoreButton(ActionEvent event){
        Stage stage = (Stage) lmUStore.getScene().getWindow();
        stage.close();
        updateStore();
    }
    public void updateItem(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateItem.fxml"));
            Parent root = loader.load();
            updateItemController controller = loader.getController();
            controller.getUser(lmWelcome.getText());
            controller.getStore(lmStore.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 600, 400));
            infoStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
    public void removeItemButton(){
        Stage stage = (Stage) lmRemove.getScene().getWindow();
        stage.close();
        removeItem();
    }

    public void removeItem(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("removeItem.fxml"));
            Parent root = loader.load();
            removeItemController controller = loader.getController();
            controller.getUser(lmWelcome.getText());
            controller.getStore(lmStore.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 600, 400));
            infoStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateStore(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateStore.fxml"));
            Parent root = loader.load();
            updateStoreController controller = loader.getController();
            controller.getUser(lmWelcome.getText());
            controller.getStore(lmStore.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 600, 400));
            infoStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM items WHERE sale = 'TRUE'");

            while(rs.next()){
                oblist.add(new tableView(rs.getString("name"),rs.getString("price"), rs.getString("isle")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lmTName.setCellValueFactory(new PropertyValueFactory<tableView,String>("name"));
        lmTPrice.setCellValueFactory(new PropertyValueFactory<tableView,String>("price"));
        lmTIsle.setCellValueFactory(new PropertyValueFactory<tableView,String>("isle"));

        lmTable.setItems(oblist);
    }
}

