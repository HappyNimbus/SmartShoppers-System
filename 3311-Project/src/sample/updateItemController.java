package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.Statement;

public class updateItemController {

@FXML
    private TextField cSName, cName, cCat, cPrice,cL,cW,cH, cIsle;
@FXML
    private TextArea cD;
@FXML
    private Label cStore, cUser, cMsg,uName;
@FXML
    private Button cStart, cUName, cUCat, cUPrice, cUAVA, cUSize, cUIsle, cUSale, cUD, cBack;
@FXML
    private RadioButton cAT, cAF, cST, cSF;

public void getStore(String store){

    cStore.setText(store);

}

public void getUser(String user){

    cUser.setText(user);
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

    String uSName = cSName.getText();

    if(cSName.getText().isEmpty()){
        cMsg.setText("Fill in Name to start");
    }
    else{
        uName.setText(uSName);
    }
}

public void updateName(){
    DBUtils connectNow = new DBUtils();
    Connection connectDB = connectNow.getConnection();

    String newName = cName.getText();
    if(cName.getText().isEmpty()){
        cMsg.setText("Fill in Name to update");
    }
    else{
        String newNameChange = "UPDATE items SET name = '" + newName + "' WHERE name = '" + uName.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(newNameChange);
            cMsg.setText("Name Change Successful");

        }catch(Exception e){
            e.printStackTrace();
        }

        uName.setText(cName.getText());
        cName.setText("");
    }
}
    public void updateCat(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newCat = cCat.getText();
        if(cCat.getText().isEmpty()){
            cMsg.setText("Fill in catagory to update");
        }
        else{
            String newChange = "UPDATE items SET catagory = '" + newCat + "' WHERE name = '" + uName.getText() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                cMsg.setText("Category Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }

            cCat.setText("");
            cMsg.setText("");
        }
    }
    public void updatePrice(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newPrice = cPrice.getText();
        if(cPrice.getText().isEmpty()){
            cMsg.setText("Fill in price to update");
        }
        else{
            String newChange = "UPDATE items SET price = '" + newPrice + "' WHERE name = '" + uName.getText() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                cMsg.setText("Catagory Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
            cPrice.setText("");
        }
    }

    public void updateSize(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newL = cL.getText();
        String newW = cW.getText();
        String newH = cH.getText();
        if(cL.getText().isEmpty() || cW.getText().isEmpty() || cH.getText().isEmpty()){
            cMsg.setText("Fill in size to update");
        }
        else{
            String newChange = "UPDATE items SET size = '" + newL + "x" + newW + "x" + newH + "' WHERE name = '" + uName.getText() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                cMsg.setText("Size Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }

            cL.setText("");
            cW.setText("");
            cH.setText("");
        }
    }
    public void updateIsle(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newIsle = cIsle.getText();
        if(cIsle.getText().isEmpty()){
            cMsg.setText("Fill in isle to update");
        }
        else{
            String newChange = "UPDATE items SET isle = '" + newIsle + "' WHERE name = '" + uName.getText() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                cMsg.setText("Isle Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
            cIsle.setText("");
        }
    }
    public void updateDesc(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String newDecs = cD.getText();
        if(cD.getText().isEmpty()){
            cMsg.setText("Fill in description to update");
        }
        else{
            String newChange = "UPDATE items SET description = '" + newDecs + "' WHERE name = '" + uName.getText() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                cMsg.setText("Description Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
            cD.setText("");
        }
    }

    public void backButton(ActionEvent event){
    Stage stage = (Stage) cBack.getScene().getWindow();
    stage.close();
    back();
    }

    public void updateAva(){
        String newAva = "TRUE";
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        if(cAT.isSelected()){
            newAva = "TRUE";
        }
        else if(cAF.isSelected()){
            newAva = "FALSE";
        }

        if(cAT.isSelected() == false && cAF.isSelected() == false){
            cMsg.setText("Select availability to update");
        }
        else{
            String newChange = "UPDATE items SET avalibilty = '" + newAva + "' WHERE name = '" + uName.getText() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                cMsg.setText("Availability Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void updateSale(){
        String newSale = "TRUE";
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        if(cST.isSelected()){
            newSale = "TRUE";
        }
        else if(cSF.isSelected()){
            newSale = "FALSE";
        }

        if(cST.isSelected() == false && cSF.isSelected() == false){
            cMsg.setText("Select availability to update");
        }
        else{
            String newChange = "UPDATE items SET sale = '" + newSale + "' WHERE name = '" + uName.getText() + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                cMsg.setText("Sale Change Successful");

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

public void back(){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginManager.fxml"));
            Parent root = loader.load();
            logInManagerController loginController = loader.getController();
            loginController.welcome(cUser.getText());
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
