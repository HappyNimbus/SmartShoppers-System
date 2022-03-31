package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private Button lgLogout;

    @FXML
    private Label lgUser;

    @FXML
    private Label lgPass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lgLogout.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event, "menu.fxml", "Login", null, null);
            }
        });

    }

    public void setUserInfo(String username, String password){
        lgUser.setText("Welcome" + username);
        lgPass.setText("Password:" + password);
    }

}




