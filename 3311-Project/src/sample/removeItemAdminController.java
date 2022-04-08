package sample;

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

public class removeItemAdminController implements Initializable {

    @FXML
    private Label adRMsg, adRUser;

    @FXML
    private ChoiceBox adRStore;

    @FXML
    private Button adRemove, adRBack;

    @FXML
    private TextField adRName;

    String addStore = null;

    public void getUser(String user){
        adRUser.setText(user);
    }

    public void removeLoginButton() {
        remove();
    }

    public void backLoginButton(){
        Stage stage = (Stage) adRBack.getScene().getWindow();
        stage.close();
        back();
    }

    public void remove(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String productName = adRName.getText();

        if(adRStore.getValue() == null){
            adRMsg.setText("No store was selected");
        }
        else if(adRName.getText().isEmpty()){
            adRMsg.setText("Information is missing");
        }
        else {
            String deleteItem = "DELETE FROM items WHERE name = '" + productName + "'" + "AND store = '" + adRStore.getValue() + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(deleteItem);
                adRMsg.setText("Item Removed");

            } catch (Exception e) {
                e.printStackTrace();
            }

            adRName.setText("");
        }
    }

    public void back(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
            Parent root = loader.load();
            loginAdminController loginController = loader.getController();
            loginController.getUser(adRUser.getText());
            Stage loggedinStage = new Stage();
            loggedinStage.initStyle(StageStyle.UNDECORATED);
            loggedinStage.setScene(new Scene(root, 600, 400));
            loggedinStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                adRStore.getItems().add(addStore);
            }


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
