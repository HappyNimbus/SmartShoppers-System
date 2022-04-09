package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class addItemAdminController implements Initializable {
    @FXML
    private ChoiceBox adAStore;
    @FXML
    private Label adAMsg;
    @FXML
    private TextField adACat;
    @FXML
    private TextField adAIsle;
    @FXML
    private TextField adAPrice;
    @FXML
    private TextField adAName;
    @FXML
    private TextField adAL,adAW,adAH;
    @FXML
    private TextArea adAD;
    @FXML
    private Label adAUser;
    @FXML
    private Button adABack;

    String addStore = null;

    public void getUser(String user){
        adAUser.setText(user);
    }

    public void addButton(ActionEvent event){
        addNewItem();
    }


    public void addNewItem(){

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String catagory = adACat.getText();
        String isle = adAIsle.getText();
        String price = adAPrice.getText();
        String name = adAName.getText();
        String L = adAL.getText();
        String W = adAW.getText();
        String H = adAH.getText();
        String description = adAD.getText();


        if(adAStore.getValue() == null){
            adAMsg.setText("No store was selected");
        }
        else if(adACat.getText().isEmpty() || adAIsle.getText().isEmpty() || adAPrice.getText().isEmpty() || adAName.getText().isEmpty() || adAL.getText().isEmpty() || adAW.getText().isEmpty() && adAH.getText().isEmpty() || adAD.getText().isEmpty() ){

            adAMsg.setText("Information is missing");
        }

        else{
            String insertFields = "INSERT INTO items (catagory, price, avalibilty, name, size, sale, store, isle, description) VALUES ('";
            String insertValues = catagory + "','" + price + "','" + "TRUE" + "','" + name + "','" + L + "x" + W + "x" + H + "','" + "FALSE" + "','" + adAStore.getValue() + "','" + isle + "','" + description + "')";
            String insertToRegister = insertFields + insertValues;

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                adAMsg.setText("Item added successfully");
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }

        }
    }


    public void backButton(ActionEvent event){
        Stage stage = (Stage) adABack.getScene().getWindow();
        stage.close();
        back();
    }

    public void back(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
            Parent root = loader.load();
            loginAdminController loginController = loader.getController();
            loginController.getUser(adAUser.getText());
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
                adAStore.getItems().add(addStore);
            }


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
