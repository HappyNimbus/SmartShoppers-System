package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.Statement;


public class updateStoreController {

    @FXML
    private Label uUser, uStore, uMsg;
    @FXML
    private Button uOHours, uCHours, uAvab, uBack;
    @FXML
    private TextField uOpen, uClose;
    @FXML
    private RadioButton uT,uF;

    public void getUser(String user){
        uUser.setText(user);
    }
    public void getStore(String store){
        uStore.setText(store);
    }
    public void updateOpenB(ActionEvent event){
        updateOpen();
    }
    public void updateCloseB(ActionEvent event){
        updateClose();
    }
    public void updateAvaB(ActionEvent event){
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
            String newChange = "UPDATE store SET open = '" + newOpen + "' WHERE store = '" + uStore.getText() + "'";

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
            String newChange = "UPDATE store SET close = '" + newClose + "' WHERE store = '" + uStore.getText() + "'";

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
            String newChange = "UPDATE store SET avalibility = '" + newAva + "' WHERE store = '" + uStore.getText() + "'";

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginManager.fxml"));
            Parent root = loader.load();
            logInManagerController loginController = loader.getController();
            loginController.welcome(uUser.getText());
            Stage loggedinStage = new Stage();
            loggedinStage.initStyle(StageStyle.UNDECORATED);
            loggedinStage.setScene(new Scene(root, 600, 400));
            loggedinStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }


}
