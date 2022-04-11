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

public class updateManagerController implements Initializable {

    @FXML
    private Label uMName, uUpdate, uDMsg, uSMsg, uOUser, uCMsg;
    @FXML
    private TextField uFirst, uLast, uUser, uPass;
    @FXML
    private ChoiceBox <String> uStore;
    @FXML
    private Button uUpF, uUpL, uUpU, uUpP, uUpS, uCreateM, uDeleteM, uStart, uBack ;

    String addStore = null;

    public void getUser(String user){
        uOUser.setText(user);
    }

    public void createB(ActionEvent event){
        String firstname = uFirst.getText();
        String lastname = uLast.getText();
        String username = uUser.getText();
        String password = uPass.getText();
        String store = uStore.getValue();
        String authentication = "Manager";

        if(uFirst.getText().isEmpty() || uLast.getText().isEmpty() || uUser.getText().isEmpty() || uPass.getText().isEmpty()){
            uCMsg.setText("Please fill out all information");
        }
        else
            uCMsg.setText("Manager registered successfully");
            updateManagerControllerBE.create(firstname, lastname, username, password, store, authentication);
    }

    public void backB(ActionEvent event){
        Stage stage = (Stage) uBack.getScene().getWindow();
        stage.close();
        back();
    }
    public void deleteB(ActionEvent event){
        String userDelete = uUser.getText();
        if(uUser.getText().isEmpty()){
            uDMsg.setText("Enter a User to Remove");
        }
        else {
            uDMsg.setText("Manager deleted successfully");
            updateManagerControllerBE.delete(userDelete);
        }
    }
    public void startUpdate(ActionEvent event){
        if(uUser.getText().isEmpty()){
            uSMsg.setText("Enter a User to Update");
        }
        else{
            uUpdate.setText("UPDATING:");
            uMName.setText(uUser.getText());
        }
    }
    public void updateFirstnameB(ActionEvent event){
        String first = uFirst.getText();
        String oldUser = uMName.getText();
        if(uMName.getText().isEmpty()){
            uSMsg.setText("Please enter a User and press 'Start Update'");
        }
        else {
            uSMsg.setText("Manager firstname update successfully");
            updateManagerControllerBE.updateFirstname(first, oldUser);
        }
    }
    public void updateLastB(ActionEvent event){
        String last = uLast.getText();
        String oldUser = uMName.getText();
        if(uMName.getText().isEmpty()){
            uSMsg.setText("Please enter a User and press 'Start Update'");
        }
        else {
            uSMsg.setText("Manager lastname update successfully");
            updateManagerControllerBE.updateLast(last, oldUser);
        }

    }

    public void updateUserNameB(ActionEvent event){
        String user = uUser.getText();
        String oldUser = uMName.getText();
        if(uMName.getText().isEmpty()){
            uSMsg.setText("Please enter a User and press 'Start Update'");
        }
        else {
            uSMsg.setText("Manager username update successfully");
            updateManagerControllerBE.updateUserName(user, oldUser);
            uMName.setText(user);
        }
    }

    public void updatePasswordB(ActionEvent event){
        String pass = uPass.getText();
        String oldUser = uMName.getText();
        if(uMName.getText().isEmpty()){
            uSMsg.setText("Please enter a User and press 'Start Update'");
        }
        else {
            uSMsg.setText("Manager password update successfully");
            updateManagerControllerBE.updatePassword(pass, oldUser);
        }
    }

    public void updateStoreB(ActionEvent event){
        String store = uStore.getValue();
        String oldUser = uMName.getText();
        if(uMName.getText().isEmpty()){
            uSMsg.setText("Please enter a User and press 'Start Update'");
        }
        else
            uSMsg.setText("Manager store update successfully");
            updateManagerControllerBE.updateStore(store, oldUser);
    }


    public void back(){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
            Parent root = loader.load();
            loginAdminController loginController = loader.getController();
            loginController.getUser(uOUser.getText());
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
                uStore.getItems().add(addStore);
            }


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
