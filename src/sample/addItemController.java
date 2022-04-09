package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.Statement;

public class addItemController {
@FXML
private Label adStore;
@FXML
private Label adMsg;
@FXML
private TextField adCat;
@FXML
private TextField adIsle;
@FXML
private TextField adPrice;
@FXML
private TextField adName;
@FXML
private TextField adL,adW,adH;
@FXML
private TextArea adD;
@FXML
private Label adUser;

public void getUser(String user){
    adUser.setText(user);
}

public void getStore(String store){
    adStore.setText(store);
}

public void addButton(ActionEvent event){
    addNewItem();
}

public void addNewItem(){

    DBUtils connectNow = new DBUtils();
    Connection connectDB = connectNow.getConnection();

    String catagory = adCat.getText();
    String isle = adIsle.getText();
    String price = adPrice.getText();
    String name = adName.getText();
    String L = adL.getText();
    String W = adW.getText();
    String H = adH.getText();
    String description = adD.getText();

    if(adCat.getText().isEmpty() || adIsle.getText().isEmpty() || adPrice.getText().isEmpty() || adName.getText().isEmpty() || adL.getText().isEmpty() || adW.getText().isEmpty() && adH.getText().isEmpty() || adD.getText().isEmpty() ){

        adMsg.setText("Information is missing");
    }
    else{
        String insertFields = "INSERT INTO items (catagory, price, avalibilty, name, size, sale, store, isle, description) VALUES ('";
        String insertValues = catagory + "','" + price + "','" + "TRUE" + "','" + name + "','" + L + "x" + W + "x" + H + "','" + "FALSE" + "','" + adStore.getText() + "','" + isle + "','" + description + "')";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            adMsg.setText("Item added successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}


public void backButton(ActionEvent event){
    back();
}

public void back(){
    try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginManager.fxml"));
        Parent root = loader.load();
        logInManagerController loginController = loader.getController();
        loginController.welcome(adUser.getText());
        Stage loggedinStage = new Stage();
        loggedinStage.initStyle(StageStyle.UNDECORATED);
        loggedinStage.setScene(new Scene(root, 778, 510));
        loggedinStage.show();

    } catch (Exception e) {
        e.printStackTrace();
        e.getCause();
    }


}

}
