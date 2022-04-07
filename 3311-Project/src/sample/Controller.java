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

public class Controller {

    @FXML
    private Button btLogin;

    @FXML
    private Button btSignup;

    @FXML
    private TextField tfUser;

    @FXML
    private TextField tfPass;

    @FXML
    private Label lnMsg;

    String auth = null;


    public void loginButtonStart(ActionEvent event){
        if(tfUser.getText().isEmpty() == false && tfPass.getText().isEmpty() == false){
            authentication();
            validateLogin();
        }
        else{
            lnMsg.setText("Please enter username and password");
        }
    }

    public void signUpButtonStart(ActionEvent event){
        Stage stage = (Stage) btSignup.getScene().getWindow();
        stage.close();
        createAccountForm();
    }

    public void authentication(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String checkAuth = "SELECT authentication FROM users WHERE username = '" + tfUser.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(checkAuth);

            while(queryResult.next()){
                auth = queryResult.getString("authentication");
            }


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void validateLogin(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM users WHERE username = '" + tfUser.getText() + "' AND password = '" + tfPass.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){

                if(queryResult.getInt(1) == 1)
                {
                    lnMsg.setText("Login Success");
                    Stage stage = (Stage) btLogin.getScene().getWindow();
                    stage.close();

                    if(auth.equals("user")) {
                        userLogInForm();
                    }
                    else if(auth.equals("Manager")){
                        managerLoginForm();
                    }

                }
                else
                {
                    lnMsg.setText("Invalid login. Please try again");
                }

            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

   public void createAccountForm() {

       try {

           Parent root = FXMLLoader.load(getClass().getResource("sign-up.fxml"));
           Stage signupStage = new Stage();
           signupStage.initStyle(StageStyle.UNDECORATED);
           signupStage.setScene(new Scene(root, 600, 400));
           signupStage.show();

       } catch (Exception e) {
           e.printStackTrace();
           e.getCause();
       }
   }

   public void userLogInForm(){
       try {

           FXMLLoader loader = new FXMLLoader(getClass().getResource("logged-in.fxml"));
           Parent root = loader.load();
           loginController loginController = loader.getController();
           loginController.welcome(tfUser.getText());
           Stage loggedinStage = new Stage();
           loggedinStage.initStyle(StageStyle.UNDECORATED);
           loggedinStage.setScene(new Scene(root, 778, 510));
           loggedinStage.show();

       } catch (Exception e) {
           e.printStackTrace();
           e.getCause();
       }
   }

   public void managerLoginForm(){
       try {

           FXMLLoader loader = new FXMLLoader(getClass().getResource("loginManager.fxml"));
           Parent root = loader.load();
           logInManagerController loginController = loader.getController();
           loginController.welcome(tfUser.getText());
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
