package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.sql.Connection;
import java.sql.Statement;

public class addStoreController {

    @FXML
    private Label sUser, sMsg;

    @FXML
    private TextField sName, sOHours, sCHours;

    @FXML
    private Button sAddStore, sBack;


    public void getUser(String user){
        sUser.setText(user);
    }

    public void addStoreButton(ActionEvent event){
        if(sName.getText().isEmpty() || sOHours.getText().isEmpty() || sCHours.getText().isEmpty()){
            sMsg.setText("Information is missing");
        }
        else
        addStore();
    }
    public void backButton(ActionEvent event){
        Stage stage = (Stage) sBack.getScene().getWindow();
        stage.close();
        back();
    }

    public void addStore(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String store = sName.getText();
        String open = sOHours.getText();
        String close = sCHours.getText();
        String avalibility = "TRUE";

        String insertFields = "INSERT INTO store (open, close, avalibility, store) VALUES ('";
        String insertValues = open + "','" + close + "','" + "TRUE" + "','" + store + "')";
        String insert = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert);
            sMsg.setText("Store added successfully");

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void back(){
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

}
