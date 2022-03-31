package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class signUpController implements Initializable{

    @FXML
    private Button suSignup;

    @FXML
    private Button bkLogin;

    @FXML
    private TextField suUser;

    @FXML
    private TextField suPass;

    @FXML
    private TextField suEmail;

    @FXML
    private TextField suFirst;

    @FXML
    private TextField suLast;

    @FXML
    private TextField suStore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        suSignup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!suUser.getText().trim().isEmpty() && !suPass.getText().trim().isEmpty() && !suEmail.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event, suUser.getText(), suPass.getText(), suEmail.getText());
                } else {
                    System.out.println("Please fill in all info");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all info");
                    alert.show();
                }
            }
        });

        bkLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "menu.fxml", "Login", null, null);
            }
        });
    }
}


