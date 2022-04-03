package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class signUpController implements Initializable {

    @FXML
    private Button suSignup;

    @FXML
    private Button bkLogin;

    @FXML
    private TextField suUser;

    @FXML
    private TextField suPass;

    @FXML
    private TextField suPass1; //change to password field later

    @FXML
    private TextField suFirst;

    @FXML
    private TextField suLast;

    @FXML
    private TextField suStore;

    @FXML
    private Label suMsg;

    @FXML
    private Label suPTest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void singUpButton(ActionEvent event){

        if (suPass.getText().equals(suPass1.getText())){
            suPTest.setText("");
            registerUser();

        } else{

            suPTest.setText("Password does not match");

        }


    }


    public void reLoginButton (ActionEvent event){
        Stage stage = (Stage) bkLogin.getScene().getWindow();
        stage.close();
        backLogin();

    }

    public void registerUser() {

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String firstname = suFirst.getText();
        String lastname = suLast.getText();
        String username = suUser.getText();
        String password = suPass.getText();
        String authentication = "user";
        String storepref = suStore.getText();

        String insertFields = "INSERT INTO users (firstname, lastname, username, password, authentication, storepref) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "','" + authentication + "','" + storepref + "')";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            suMsg.setText("User registered successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

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
}

