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
        String newName = adCName.getText();
        String store = (String) adCStore.getValue();
        String oldName = adUName.getText();
        if(adCName.getText().isEmpty()){
            adCMsg.setText("Fill in Name to update");
        }
        else{
            adCMsg.setText("Name Change Successful");
            updateItemBE.updateName(newName, oldName, store);
            adUName.setText(adCName.getText());
        }
    }
    public void updateCatB(ActionEvent event){
        String newCat = adCCat.getText();
        String store = (String) adCStore.getValue();
        String oldName = adUName.getText();
        if(adCCat.getText().isEmpty()){
            adCMsg.setText("Fill in catagory to update");
        }
        else{
            adCMsg.setText("Category Change Successful");
            updateItemBE.updateCat(newCat, oldName, store);
        }
    }
    public void updatePriceB(ActionEvent event){
        String newPrice = adCPrice.getText();
        String store = (String) adCStore.getValue();
        String oldName = adUName.getText();
        if(adCPrice.getText().isEmpty()){
            adCMsg.setText("Fill in price to update");
        }
        else{
            adCMsg.setText("Size Change Successful");
            updateItemBE.updatePrice(newPrice, oldName, store);
        }
    }
    public void updateAvaB(ActionEvent event){
        String newAva = "";
        if(adCAT.isSelected()){
            newAva = "TRUE";
        }
        else if(adCAF.isSelected()){
            newAva = "FALSE";
        }
        String store = (String) adCStore.getValue();
        String oldName = adUName.getText();
        if(adCAT.isSelected() == false && adCAF.isSelected() == false){
            adCMsg.setText("Select availability to update");
        }
        else {
            adCMsg.setText("Availability Change Successful");
            updateItemBE.updateAva(newAva, oldName, store);
        }
    }
    public void updateSizeB(ActionEvent event){
        String newL = adCL.getText();
        String newW = adCW.getText();
        String newH = adCH.getText();
        String store = (String) adCStore.getValue();
        String oldName = adUName.getText();
        if(adCL.getText().isEmpty() || adCW.getText().isEmpty() || adCH.getText().isEmpty()){
            adCMsg.setText("Fill in size to update");
        }
        else {
            adCMsg.setText("Size Change Successful");
            updateItemBE.updateSize(newL,newW,newH,oldName,store);
        }
    }
    public void updateIsleB(ActionEvent event) {
        String newIsle = adCIsle.getText();
        String store = (String) adCStore.getValue();
        String oldName = adUName.getText();
        if(adCIsle.getText().isEmpty()){
            adCMsg.setText("Fill in isle to update");
        }
        else {
            adCMsg.setText("Isle Change Successful");
            updateItemBE.updateIsle(newIsle, oldName, store);
        }
    }
    public void updateSaleB(ActionEvent event){
        String newSale = "";
        String store = (String) adCStore.getValue();
        String oldName = adUName.getText();
        if(adCST.isSelected()){
            newSale = "TRUE";
        }
        else if(adCSF.isSelected()){
            newSale = "FALSE";
        }
        if(adCST.isSelected() == false && adCSF.isSelected() == false){
            adCMsg.setText("Select availability to update");
        }
        else {
            adCMsg.setText("Sale Change Successful");
            updateItemBE.updateSale(newSale, oldName, store);
        }
    }
    public void updateDescB(ActionEvent event){
        String newDecs = adCD.getText();
        String store = (String) adCStore.getValue();
        String oldName = adUName.getText();
        if(adCD.getText().isEmpty()){
            adCMsg.setText("Fill in description to update");
        }
        else {
            adCMsg.setText("Description Change Successful");
            updateItemBE.updateDesc(newDecs, oldName, store);
        }
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
            adCMsg.setText("Isle Change Successful");
            adUName.setText(uSName);
        }
    }

    public void backButton(ActionEvent event){
        Stage stage = (Stage) adBack.getScene().getWindow();
        stage.close();
        back();
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
