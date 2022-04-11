package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
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
    private TextField suPass1;

    @FXML
    private TextField suFirst;

    @FXML
    private TextField suLast;

    @FXML
    private ChoiceBox<String> suStore;

    @FXML
    private Label suMsg;

    @FXML
    private Label suPTest;

    @FXML
    private Button suAdmin;

    String addStore = null;


    @Override
    public void initialize(URL url, ResourceBundle arg){
        loadData();
    }

    public void loadData(){

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String getLocations = "SELECT * FROM store WHERE avalibility = 'TRUE'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet getStore = statement.executeQuery(getLocations);

            while (getStore.next()) {
               addStore = getStore.getString("store");
               suStore.getItems().add(addStore);
            }


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    public void singUpButton(ActionEvent event){

            suPTest.setText("");
            String firstname = suFirst.getText();
            String lastname = suLast.getText();
            String username = suUser.getText();
            String password = suPass.getText();
            String authentication = "user";
            String storepref = suStore.getValue();
            String password2 = suPass1.getText();
            signUpControllerBE.registerUser(firstname, lastname, username, password, password2, authentication, storepref);

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM users WHERE username = '" + username + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {

                if (queryResult.getInt(1) == 1) {
                    suMsg.setText("User already exists");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (password.equals(password2)) {
            suMsg.setText("User registered successfully");
        }
        else{
            suMsg.setText("Password does not match");
        }

    }


    public void reLoginButton (ActionEvent event){
        Stage stage = (Stage) bkLogin.getScene().getWindow();
        stage.close();
        backLogin();

    }

    public void adminButton(ActionEvent event){
        Stage stage = (Stage) suAdmin.getScene().getWindow();
        stage.close();
        adminSignup();
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

    public void adminSignup(){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("sign-up-admin.fxml"));
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

