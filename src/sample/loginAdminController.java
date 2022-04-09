package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class loginAdminController implements Initializable {

    @FXML
    private Button aADD, aRemove, aUpdate, aUStore, aAddStore, aRemoveStore,  aUMan, aLogout, aCheck;
    @FXML
    private Label aWelcome;
    @FXML
    private ChoiceBox <String> aStoreCheck;

    String addStore = null;


    public void getUser(String user){
        aWelcome.setText(user);
    }

    public void addItemB(ActionEvent event){
        Stage stage = (Stage) aADD.getScene().getWindow();
        stage.close();
        addItem();
    }

    public void removeItemB(ActionEvent event){
        Stage stage = (Stage) aRemove.getScene().getWindow();
        stage.close();
        removeItem();
    }

    public void updateItemB(ActionEvent event){
        Stage stage = (Stage) aUpdate.getScene().getWindow();
        stage.close();
        updateItem();
    }

    public void updateStoreB(ActionEvent event){
        Stage stage = (Stage) aUStore.getScene().getWindow();
        stage.close();
        updateStore();
    }

    public void addStoreB(ActionEvent event){
        Stage stage = (Stage) aAddStore.getScene().getWindow();
        stage.close();
        addStore();
    }

    public void removeStoreB(ActionEvent event){
        Stage stage = (Stage) aRemoveStore.getScene().getWindow();
        stage.close();
        removeStore();
    }

    public void updateManagerB(ActionEvent event){
        Stage stage = (Stage) aUMan.getScene().getWindow();
        stage.close();
        updateManager();
    }

    public void checkB(ActionEvent event){
        Stage stage = (Stage) aCheck.getScene().getWindow();
        stage.close();
        check();
    }

    public void check(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("salesAdmin.fxml"));
            Parent root = loader.load();
            salesAdminController controller = loader.getController();
            controller.getUser(aWelcome.getText());
            controller.getStore(aStoreCheck.getValue());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 600, 400));
            infoStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void addStore(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addStore.fxml"));
            Parent root = loader.load();
            addStoreController controller = loader.getController();
            controller.getUser(aWelcome.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 600, 400));
            infoStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void addItem(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addItemAdmin.fxml"));
            Parent root = loader.load();
            addItemAdminController controller = loader.getController();
            controller.getUser(aWelcome.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 600, 400));
            infoStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void removeItem(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("removeItemAdmin.fxml"));
            Parent root = loader.load();
            removeItemAdminController controller = loader.getController();
            controller.getUser(aWelcome.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 600, 400));
            infoStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateItem(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateItemAdmin.fxml"));
            Parent root = loader.load();
            updateItemAdminController controller = loader.getController();
            controller.getUser(aWelcome.getText());
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateStoreAdmin.fxml"));
            Parent root = loader.load();
            updateStoreAdminController controller = loader.getController();
            controller.getUser(aWelcome.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 600, 400));
            infoStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void removeStore(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("removeStore.fxml"));
            Parent root = loader.load();
            removeStoreController controller = loader.getController();
            controller.getUser(aWelcome.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 600, 400));
            infoStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateManager(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateManager.fxml"));
            Parent root = loader.load();
            updateManagerController controller = loader.getController();
            controller.getUser(aWelcome.getText());
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
        loadData();
    }
    public void loadData(){

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String getLocations = "SELECT * FROM store";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet getStore = statement.executeQuery(getLocations);

            while (getStore.next()) {
                addStore = getStore.getString("store");
                aStoreCheck.getItems().add(addStore);
            }


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void logOutButton(ActionEvent event){
        Stage stage = (Stage) aLogout.getScene().getWindow();
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

}
