package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class loginController{

    @FXML
    private Button btLogOut;

    @FXML
    private Button btCInfo;

    @FXML
    private Button btCStore;

    @FXML
    private Label lWelcome;




    public void changeInfoButton(){
        Stage stage = (Stage) btCInfo.getScene().getWindow();
        stage.close();
        changeInfoScene();

    }



    public String welcome(String userName){
        lWelcome.setText(userName);
        String username = String.valueOf(lWelcome);

        return username;

    }


    public void logOutButton(ActionEvent event){
        Stage stage = (Stage) btLogOut.getScene().getWindow();
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

    public void changeInfoScene(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("change-info.fxml"));
            Parent root = loader.load();
            ChangeInfoController changeInfoController = loader.getController();
            changeInfoController.getName(lWelcome.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 778, 510));
            infoStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }


}




