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

public class ChangeStoreController {

    @FXML
    private Label scLabel;

    @FXML
    private TextField scChangeL;

    @FXML
    private Label scUser;

    @FXML
    private Button scChange;

    @FXML
    private Button scClose;



    public void getName(String user){

        scUser.setText(user);

    }

    public void changeButton(ActionEvent event){

        changeLocationInfo();

    }

    public void backButton(ActionEvent event){
        Stage stage = (Stage) scClose.getScene().getWindow();
        stage.close();
        back();
    }

    public void changeLocationInfo() {

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String changedLocation = scChangeL.getText();
        String originalUser = scUser.getText();


        if(scChangeL.getText().isEmpty() == false){

            String userChange = "UPDATE users SET storepref = '" + changedLocation + "' WHERE username = '" + originalUser + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(userChange);
                scLabel.setText("Location Change Successful");


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

            scChangeL.setText("");

        }


        else{
            scLabel.setText("No Changes Made");
        }
    }

    public void back(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("logged-in.fxml"));
            Parent root = loader.load();
            loginController loginController = loader.getController();
            loginController.welcome(scUser.getText());
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
