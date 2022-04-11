package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Statement;

public class removeItemController {

    @FXML
    private Label rMsg, rUser, rStore;

    @FXML
    private Button rRemove, rBack;

    @FXML
    private TextField rName;

    public void getUser(String user){
        rUser.setText(user);
    }

    public void getStore(String store){
        rStore.setText(store);
    }

    public void removeLoginButton() {
        String productName = rName.getText();
        String store = rStore.getText();

        if(rName.getText().isEmpty()){
            rMsg.setText("Information is missing");
        }
        else {
            rMsg.setText("Item Removed");
            removeItemAdminControllerBE.remove(productName,store);
        }
        removeItemControllerBE.remove(productName,store);
    }

    public void backLoginButton(){
    Stage stage = (Stage) rBack.getScene().getWindow();
        stage.close();
        back();
    }

    public void back(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginManager.fxml"));
            Parent root = loader.load();
            logInManagerController loginController = loader.getController();
            loginController.welcome(rUser.getText());
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
