package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class updateItemAdminController implements Initializable {


    @FXML
    private TextField adCSName, adCName, adCCat, adCPrice,adCL,adCW,adCH, adCIsle;
    @FXML
    private TextArea adCD;
    @FXML
    private Label adCUser, adCMsg, adUName;
    @FXML
    private ChoiceBox adCStore;
    @FXML
    private Button adCStart, adCUName, adCUCat, adCUPrice, adCUAVA, adCUSize, adCUIsle, adCUSale, adCUD, adBack;
    @FXML
    private RadioButton adCAT, adCAF, adCST, adCSF;

    String addStore = null;

    public void getUser(String user){
        adCUser.setText(user);
    }

    public void startButton(ActionEvent event){
        start();
    }
    public void updateNameB(ActionEvent event){
        updateName();
    }
    public void updateCatB(ActionEvent event){
        updateCat();
    }
    public void updatePriceB(ActionEvent event){
        updatePrice();
    }
    public void updateAvaB(ActionEvent event){
        updateAva();
    }
    public void updateSizeB(ActionEvent event){
        updateSize();
    }
    public void updateIsleB(ActionEvent event) {
        updateIsle();
    }
    public void updateSaleB(ActionEvent event){
        updateSale();
    }
    public void updateDescB(ActionEvent event){
        updateDesc();
    }

    public void start(){

        String uSName = adCSName.getText();

        if(adCStore.getValue() == null){
            adCMsg.setText("Select Store to start");
        }
        else if(adCSName.getText().isEmpty()){
            adCMsg.setText("Fill in Name to start");
        }
        else{
            adUName.setText(uSName);
        }
    }

    public void updateName(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newName = adCName.getText();
        if(adCName.getText().isEmpty()){
            adCMsg.setText("Fill in Name to update");
        }
        else{
            String newNameChange = "UPDATE items SET name = '" + newName + "' WHERE name = '" + adCName.getText() + "'" + "AND store = '" + adCStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newNameChange);
                adCMsg.setText("Name Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }

            adUName.setText(adCName.getText());
            adCName.setText("");
        }
    }
    public void updateCat(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newCat = adCCat.getText();
        if(adCCat.getText().isEmpty()){
            adCMsg.setText("Fill in catagory to update");
        }
        else{
            String newChange = "UPDATE items SET catagory = '" + newCat + "' WHERE name = '" + adUName.getText() + "'" + " AND store = '" + adCStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                adCMsg.setText("Category Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }

            adCCat.setText("");
            adCMsg.setText("");
        }
    }
    public void updatePrice(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newPrice = adCPrice.getText();
        if(adCPrice.getText().isEmpty()){
            adCMsg.setText("Fill in price to update");
        }
        else{
            String newChange = "UPDATE items SET price = '" + newPrice + "' WHERE name = '" + adUName.getText() + "'"+ " AND store = '" + adCStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                adCMsg.setText("Catagory Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
            adCPrice.setText("");
        }
    }

    public void updateSize(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newL = adCL.getText();
        String newW = adCW.getText();
        String newH = adCH.getText();
        if(adCL.getText().isEmpty() || adCW.getText().isEmpty() || adCH.getText().isEmpty()){
            adCMsg.setText("Fill in size to update");
        }
        else{
            String newChange = "UPDATE items SET size = '" + newL + "x" + newW + "x" + newH + "' WHERE name = '" + adUName.getText() + "'"+ " AND store = '" + adCStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                adCMsg.setText("Size Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }

            adCL.setText("");
            adCW.setText("");
            adCH.setText("");
        }
    }
    public void updateIsle(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newIsle = adCIsle.getText();
        if(adCIsle.getText().isEmpty()){
            adCMsg.setText("Fill in isle to update");
        }
        else{
            String newChange = "UPDATE items SET isle = '" + newIsle + "' WHERE name = '" + adUName.getText() + "'" + " AND store = '" + adCStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                adCMsg.setText("Isle Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
            adCIsle.setText("");
        }
    }
    public void updateDesc(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newDecs = adCD.getText();
        if(adCD.getText().isEmpty()){
            adCMsg.setText("Fill in description to update");
        }
        else{
            String newChange = "UPDATE items SET description = '" + newDecs + "' WHERE name = '" + adUName.getText() + "'" + " AND store = '" + adCStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                adCMsg.setText("Description Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
            adCD.setText("");
        }
    }

    public void backButton(ActionEvent event){
        Stage stage = (Stage) adBack.getScene().getWindow();
        stage.close();
        back();
    }

    public void updateAva(){
        String newAva = "TRUE";
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        if(adCAT.isSelected()){
            newAva = "TRUE";
        }
        else if(adCAF.isSelected()){
            newAva = "FALSE";
        }

        if(adCAT.isSelected() == false && adCAF.isSelected() == false){
            adCMsg.setText("Select availability to update");
        }
        else{
            String newChange = "UPDATE items SET avalibilty = '" + newAva + "' WHERE name = '" + adUName.getText() + "'" + " AND store = '" + adCStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                adCMsg.setText("Availability Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void updateSale(){
        String newSale = "TRUE";
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        if(adCST.isSelected()){
            newSale = "TRUE";
        }
        else if(adCSF.isSelected()){
            newSale = "FALSE";
        }

        if(adCST.isSelected() == false && adCSF.isSelected() == false){
            adCMsg.setText("Select availability to update");
        }
        else{
            String newChange = "UPDATE items SET sale = '" + newSale + "' WHERE name = '" + adUName.getText() + "'" + " AND store = '" + adCStore.getValue() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                adCMsg.setText("Sale Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void back(){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
            Parent root = loader.load();
            loginAdminController loginController = loader.getController();
            loginController.getUser(adCUser.getText());
            Stage loggedinStage = new Stage();
            loggedinStage.initStyle(StageStyle.UNDECORATED);
            loggedinStage.setScene(new Scene(root, 600, 400));
            loggedinStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


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
                adCStore.getItems().add(addStore);
            }


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }
}
