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

public class removeStoreController {

    @FXML
    public Label rUser, rMsg;
    @FXML
    public TextField rName;
    @FXML
    public Button rRemove, rBack;


    public void getUser(String user){
        rUser.setText(user);
    }

    public void backB(ActionEvent event){
        Stage stage = (Stage) rBack.getScene().getWindow();
        stage.close();
        back();
    }

    public void removeB(ActionEvent event){
        String storeName = rName.getText();
        if(rName.getText().isEmpty()){
            rMsg.setText("Information is missing");
        }
        else{
            rMsg.setText("Store Removed");
            removeStoreControllerBE.remove(storeName);
        }
    }


    public void back(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
            Parent root = loader.load();
            loginAdminController loginController = loader.getController();
            loginController.getUser(rUser.getText());
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
