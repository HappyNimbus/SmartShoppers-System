package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;

public class ChangeStoreController implements Initializable {

    @FXML
    private Label scLabel;

    @FXML
    private ChoiceBox<String> scChangeL;
    @FXML
    private TextField scLocation;
    @FXML
    private Label scUser, scMsg;

    @FXML
    private Button scChange,scSearch;

    @FXML
    private Button scClose;
    @FXML
    private TableView<tableViewStore> scTable;
    @FXML
    private TableColumn<tableViewStore, String> scStore, scAva, scDistance;

    String addStore = null;
    ObservableList<tableViewStore> oblist = FXCollections.observableArrayList();

    public void getName(String user){

        scUser.setText(user);

    }

    public void changeButton(ActionEvent event){
        String changedLocation = scChangeL.getValue();
        String originalUser = scUser.getText();

        if(scChangeL.getValue() != null) {
            scLabel.setText("Location Change Successful");
            ChangeStoreControllerBE.changeLocationInfo(changedLocation, originalUser);
        }
        else{
            scLabel.setText("No Changes Made");
        }
    }

    public void backButton(ActionEvent event){
        Stage stage = (Stage) scClose.getScene().getWindow();
        stage.close();
        back();
    }

    public void back(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("logged-in.fxml"));
            Parent root = loader.load();
            loginController loginController = loader.getController();
            loginController.welcome(scUser.getText());
            Stage loggedinStage = new Stage();
            loggedinStage.initStyle(StageStyle.UNDECORATED);
            loggedinStage.setScene(new Scene(root, 1117, 576));
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

        String getLocations = "SELECT * FROM store WHERE avalibility = 'TRUE'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet getStore = statement.executeQuery(getLocations);

            while (getStore.next()) {
                addStore = getStore.getString("store");
                scChangeL.getItems().add(addStore);
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void searchB(ActionEvent event){
        if(scLocation.getText().isEmpty()){
            scMsg.setText("Enter location");
        }
        else
        table();
    }

    public void table(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        Random r = new Random();

        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM store");

            while(rs.next()){
                double randomValue = 1 + (10 - 1) * r.nextDouble();
                DecimalFormat df = new DecimalFormat("0.00");
                randomValue = Double.parseDouble(df.format(randomValue));
                String dist = Double.toString(randomValue);
                dist = dist + "km";
                oblist.add(new tableViewStore(rs.getString("store"),rs.getString("avalibility"),dist));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scStore.setCellValueFactory(new PropertyValueFactory<tableViewStore,String>("store"));
        scAva.setCellValueFactory(new PropertyValueFactory<tableViewStore,String>("ava"));
        scDistance.setCellValueFactory(new PropertyValueFactory<tableViewStore,String>("distance"));

        scTable.setItems(oblist);
    }
}
