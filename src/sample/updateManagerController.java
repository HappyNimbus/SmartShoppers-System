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
        if(uFirst.getText().isEmpty() || uLast.getText().isEmpty() || uUser.getText().isEmpty() || uPass.getText().isEmpty()){
            uCMsg.setText("Please fill out all information");
        }
        else
            create();
    }

    public void backB(ActionEvent event){
        Stage stage = (Stage) uBack.getScene().getWindow();
        stage.close();
        back();
    }
    public void deleteB(ActionEvent event){
        if(uUser.getText().isEmpty()){
            uDMsg.setText("Enter a User to Remove");
        }
        else
            delete();
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
        if(uMName.getText().isEmpty()){
            uSMsg.setText("Please enter a User and press 'Start Update'");
        }
        else
            updateFirstname();
    }
    public void updateLastB(ActionEvent event){
        if(uMName.getText().isEmpty()){
            uSMsg.setText("Please enter a User and press 'Start Update'");
        }
        else
            updateLast();
    }

    public void updateUserNameB(ActionEvent event){
        if(uMName.getText().isEmpty()){
            uSMsg.setText("Please enter a User and press 'Start Update'");
        }
        else
            updateUserName();
    }

    public void updatePasswordB(ActionEvent event){
        if(uMName.getText().isEmpty()){
            uSMsg.setText("Please enter a User and press 'Start Update'");
        }
        else
            updatePassword();
    }

    public void updateStoreB(ActionEvent event){
        if(uMName.getText().isEmpty()){
            uSMsg.setText("Please enter a User and press 'Start Update'");
        }
        else
            updateStore();
    }


    public void create(){

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String firstname = uFirst.getText();
        String lastname = uLast.getText();
        String username = uUser.getText();
        String password = uPass.getText();
        String store = uStore.getValue();
        String authentication = "Manager";

        String insertFields = "INSERT INTO users (firstname, lastname, username, password, authentication, storepref) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "','" + authentication + "','" + store + "')";
        String insertToRegister = insertFields + insertValues;
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            uCMsg.setText("Manager registered successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void delete(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String userDelete = uUser.getText();
        String deleteInput = "DELETE FROM users WHERE username = '" + userDelete + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteInput);
            uDMsg.setText("Manager deleted successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateFirstname(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String first = uFirst.getText();
        String updateFirst = "UPDATE users SET firstname = '" + first + "' WHERE username = '" + uMName.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFirst);
            uSMsg.setText("Manager firstname update successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void updateLast(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String last = uLast.getText();
        String updateFirst = "UPDATE users SET lastname = '" + last + "' WHERE username = '" + uMName.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFirst);
            uSMsg.setText("Manager lastname update successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateUserName(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String user = uUser.getText();
        String updateFirst = "UPDATE users SET username = '" + user + "' WHERE username = '" + uMName.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFirst);
            uSMsg.setText("Manager username update successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        uMName.setText(user);
    }
    public void updatePassword(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String pass = uPass.getText();
        String updateFirst = "UPDATE users SET password = '" + pass + "' WHERE username = '" + uMName.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFirst);
            uSMsg.setText("Manager password update successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void updateStore(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String store = uStore.getValue();
        String updateFirst = "UPDATE users SET storePref = '" + store + "' WHERE username = '" + uMName.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFirst);
            uSMsg.setText("Manager store update successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
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
