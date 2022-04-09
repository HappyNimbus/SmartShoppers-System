package sample;

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

        String getLocations = "SELECT * FROM store";

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

        if(aPass.getText().equals(aCPass.getText())){
            aLabel.setText("");
            registerAdmin();

        } else{
            aLabel.setText("Password does not match");
        }

    }

    public void backButton(ActionEvent event){
        Stage stage = (Stage) aBack.getScene().getWindow();
        stage.close();
        backReg();

    }

    public void registerAdmin(){

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String firstname = aFirst.getText();
        String lastname = aLast.getText();
        String username = aUser.getText();
        String password = aPass.getText();
        String code = aCode.getText();
        String authentication = "";
        String store = aStore.getValue();

        if(code.equals("101")){
            authentication = "Admin";

            if(aStore.getValue().isEmpty()) {

                store = "ALL";
                String insertFields = "INSERT INTO users (firstname, lastname, username, password, authentication, storepref) VALUES ('";
                String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "','" + authentication + "','" + store + "')";
                String insertToRegister = insertFields + insertValues;
                try{
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(insertToRegister);
                    aMsg.setText("Admin registered successfully");
                }catch (Exception e){
                    e.printStackTrace();
                    e.getCause();
                }

            }
            else{
                aAdmin.setText("Please do not enter a store");
                aStore.setValue("Select Store");
            }
        }
        else if(code.equals("111")){
            authentication = "Manager";
            String insertFields = "INSERT INTO users (firstname, lastname, username, password, authentication, storepref) VALUES ('";
            String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "','" + authentication + "','" + store + "')";
            String insertToRegister = insertFields + insertValues;
            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                aMsg.setText("Manager registered successfully");
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
        else{
            aAuth.setText("Code does not match");
        }
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
