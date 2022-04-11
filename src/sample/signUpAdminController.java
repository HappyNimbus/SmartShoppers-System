package sample;

import com.sun.deploy.security.SelectableSecurityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class signUpAdminController implements Initializable {

    @FXML
    private TextField aFirst;
    @FXML
    private TextField aLast;
    @FXML
    private TextField aUser;
    @FXML
    private TextField aPass;
    @FXML
    private TextField aCPass;
    @FXML
    private ChoiceBox<String> aStore;
    @FXML
    private TextField aCode;
    @FXML
    private Button aSignup;
    @FXML
    private Button aBack;
    @FXML
    private Label aLabel;
    @FXML
    private Label aAuth;
    @FXML
    private Label aMsg;
    @FXML
    private Label aAdmin;

    String addStore = null;

    @Override
    public void initialize(URL url, ResourceBundle arg){
        aStore.setValue("Select Store");
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
                aStore.getItems().add(addStore);
            }


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void signUpButton(ActionEvent event){
        String firstname = aFirst.getText();
        String lastname = aLast.getText();
        String username = aUser.getText();
        String password = aPass.getText();
        String password2 = aCPass.getText();
        String code = aCode.getText();
        String authentication = "";
        String store = aStore.getValue();

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM users WHERE username = '" + username + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {

                if (queryResult.getInt(1) == 1) {
                    aLabel.setText("User already exists");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (aPass.getText().equals(aCPass.getText())) {
                    signUpAdminControllerBE.registerAdmin(firstname, lastname, username, password, password2, authentication, store, code);
                } else {
                    aLabel.setText("Password does not match");
                }

                if (code.equals("101")) {
                    aLabel.setText("Admin Registered Successfully");
                } else if (code.equals("111")) {
                    aLabel.setText("Manager Registered Successfully");
                } else {
                    aAuth.setText("Code does not match");
                }
    }

    public void backButton(ActionEvent event){
        Stage stage = (Stage) aBack.getScene().getWindow();
        stage.close();
        backReg();

    }

    public void backReg(){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("sign-up.fxml"));
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
