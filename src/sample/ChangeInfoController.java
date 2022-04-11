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
        String changedUser = chUser.getText();
        String changedPass = chPass.getText();
        String originalUser = chName.getText();

        if(chUser.getText().isEmpty() == false && chPass.getText().isEmpty() == false) {
            lbChange.setText("Changes Successful");
            ChangeInfoControllerBE.changeUserInfo(changedUser, changedPass, originalUser);
            chName.setText(changedUser);
        }
        else if (chUser.getText().isEmpty() == false) {
            lbChange.setText("Username Change Successful");
            ChangeInfoControllerBE.changeUserInfo(changedUser, changedPass, originalUser);
            chName.setText(changedUser);
        }
        else if(chPass.getText().isEmpty() == false){
            lbChange.setText("Password Change Successful");
            ChangeInfoControllerBE.changeUserInfo(changedUser, changedPass, originalUser);
        }
        else{
            lbChange.setText("No Changes Made");
        }


    }

    public void deleteAccountButton(ActionEvent event){
        String originalUser = chName.getText();
        ChangeInfoControllerBE.deleteAccount(originalUser);
        lbChange.setText("User Deleted");
        deleted();
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
            loggedinStage.setScene(new Scene(root, 1117, 576));
            loggedinStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void deleted(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();
            loginController loginController = loader.getController();
            loginController.welcome(chName.getText());
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
