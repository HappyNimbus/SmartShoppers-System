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
import java.sql.ResultSet;
import java.sql.Statement;


public class ChangeInfoController {

    @FXML
    private Button btChange;

    @FXML
    private Button btClose;

    @FXML
    private Label lbChange;

    @FXML
    private TextField chUser;

    @FXML
    private TextField chPass;

    @FXML
    private Label chName;


    public void getName(String user){

        chName.setText(user);

    }

    public void changeButton(ActionEvent event){

        changeUserInfo();

    }

    public void deleteAccountButton(ActionEvent event){

        deleteAccount();
    }


    public void changeUserInfo() {

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String changedUser = chUser.getText();
        String changedPass = chPass.getText();
        String originalUser = chName.getText();


           if(chUser.getText().isEmpty() == false && chPass.getText().isEmpty() == false){

            String userChange = "UPDATE users SET username = '" + changedUser + "' WHERE username = '" + originalUser + "'";
            String passChange = "UPDATE users SET password = '" + changedPass + "' WHERE username = '" + originalUser + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(passChange);
                statement.executeUpdate(userChange);
                lbChange.setText("Changes Successful");


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

            chName.setText(chUser.getText());
            chUser.setText("");
            chPass.setText("");

        }

        else if (chUser.getText().isEmpty() == false) {

            String userChange = "UPDATE users SET username = '" + changedUser + "' WHERE username = '" + originalUser + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(userChange);
                lbChange.setText("Username Change Successful");


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

            chName.setText(chUser.getText());
            chUser.setText("");

        }
        else if(chPass.getText().isEmpty() == false){
            String passChange = "UPDATE users SET password = '" + changedPass + "' WHERE username = '" + originalUser + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(passChange);
                lbChange.setText("Password Change Successful");


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            chName.setText(chUser.getText());
            chPass.setText("");


        }

        else{
            lbChange.setText("No Changes Made");
        }
    }

    public void deleteAccount(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String originalUser = chName.getText();

        String deleteUser = "DELETE FROM users WHERE username = '" + originalUser + "'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteUser);


        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void backButton(ActionEvent event){
        Stage stage = (Stage) btClose.getScene().getWindow();
        stage.close();
        back();
    }

    public void back(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("logged-in.fxml"));
            Parent root = loader.load();
            loginController loginController = loader.getController();
            loginController.welcome(chName.getText());
            Stage loggedinStage = new Stage();
            loggedinStage.initStyle(StageStyle.UNDECORATED);
            loggedinStage.setScene(new Scene(root, 778, 510));
            loggedinStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
