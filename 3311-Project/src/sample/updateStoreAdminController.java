package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class updateStoreAdminController implements Initializable {

    @FXML
    private Label uUser, uMsg;
    @FXML
    private ChoiceBox uStore;
    @FXML
    private Button uOHours, uCHours, uAvab, uBack;
    @FXML
    private TextField uOpen, uClose;
    @FXML
    private RadioButton uT,uF;
    String addStore = null;
    public void getUser(String user){
        uUser.setText(user);
    }
    public void updateOpenB(ActionEvent event){

        if(uStore.getValue() == null){
            uMsg.setText("Select a store");
        }
        else
        updateOpen();
    }
    public void updateCloseB(ActionEvent event){
        if(uStore.getValue() == null){
            uMsg.setText("Select a store");
        }
        else
        updateClose();
    }
    public void updateAvaB(ActionEvent event){
        if(uStore.getValue() == null){
            uMsg.setText("Select a store");
        }
        else
        updateAva();
    }
    public void backB(ActionEvent event){
        Stage stage = (Stage) uBack.getScene().getWindow();
        stage.close();
        backToM();
    }
    public void updateOpen(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newOpen = uOpen.getText();
        if(uOpen.getText().isEmpty()){
            uMsg.setText("Fill in opening hours to update");
        }
        else{
            String newChange = "UPDATE store SET open = '" + newOpen + "' WHERE store = '" + uStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                uMsg.setText("Opening Hours Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
            uClose.setText("");
        }
    }

    public void updateClose(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newClose = uClose.getText();
        if(uClose.getText().isEmpty()){
            uMsg.setText("Fill in closing hours to update");
        }
        else{
            String newChange = "UPDATE store SET close = '" + newClose + "' WHERE store = '" + uStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                uMsg.setText("Closing Hours Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
            uClose.setText("");
        }
    }

    public void updateAva(){
        String newAva = "TRUE";
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        if(uT.isSelected()){
            newAva = "TRUE";
        }
        else if(uF.isSelected()){
            newAva = "FALSE";
        }

        if(uT.isSelected() == false && uF.isSelected() == false){
            uMsg.setText("Select availability to update");
        }
        else{
            String newChange = "UPDATE store SET avalibility = '" + newAva + "' WHERE store = '" + uStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                uMsg.setText("Availability Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void backToM(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
            Parent root = loader.load();
            loginAdminController loginController = loader.getController();
            loginController.getUser(uUser.getText());
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
