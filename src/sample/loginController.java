package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class loginController implements Initializable{

    @FXML
    private Button btLogOut;

    @FXML
    private Button btCInfo;

    @FXML
    private Button btCStore;

    @FXML
    private Label lWelcome, aMsg, rMsg, lStore;

    @FXML
    private Button searchB, searchCatB, addB, addB1, removeB;
    @FXML
    private ChoiceBox<String> searchCat;
    @FXML
    private TextField searchName;
    @FXML
    private TableView<search> searchTable;
    @FXML
    private TableColumn<search, String> srTName, srTCat, srTSize, srTPrice, srTAva, srTIsle, srTD;
    @FXML
    private TableView<sale> saleTable;
    @FXML
    private TableColumn<sale, String> saTName, saTCat, saTSize, saTPrice, saTAva, saTIsle, saTD;
    @FXML
    private TableView<cartView> cart;
    @FXML
    private TableColumn<cartView, String> cName, cIsle, cPrice;

    String addCat = null;
    ObservableList<search> searchT = FXCollections.observableArrayList();
    ObservableList<sale> saleT = FXCollections.observableArrayList();
    ObservableList<cartView> cartT = FXCollections.observableArrayList();

    public void changeInfoButton(){
        Stage stage = (Stage) btCInfo.getScene().getWindow();
        stage.close();
        changeInfoScene();

    }

    public void changeStoreButton(){
        Stage stage = (Stage) btCStore.getScene().getWindow();
        stage.close();
        changeStoreScene();
    }



    public String welcome(String userName){
        lWelcome.setText(userName);
        String username = String.valueOf(lWelcome);
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String getStore = "SELECT storepref FROM users WHERE username = '" + lWelcome.getText() + "'";

        Statement statement = null;
        try {
            statement = connectDB.createStatement();
            ResultSet getStorePref = statement.executeQuery(getStore);

            while(getStorePref.next()){
                lStore.setText(getStorePref.getString("storepref"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String storeNew = lStore.getText();
        loadData(storeNew);
        saleTable(storeNew);
        cart(storeNew, lWelcome.getText());
        return username;

    }


    public void logOutButton(ActionEvent event){
        Stage stage = (Stage) btLogOut.getScene().getWindow();
        stage.close();
        backLogin();
    }

    public void backLogin() {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage backStage = new Stage();
            backStage.initStyle(StageStyle.UNDECORATED);
            backStage.setScene(new Scene(root, 600, 400));
            backStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void changeInfoScene(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("change-info.fxml"));
            Parent root = loader.load();
            ChangeInfoController changeInfoController = loader.getController();
            changeInfoController.getName(lWelcome.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 380, 400));
            infoStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void changeStoreScene(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("change-store.fxml"));
            Parent root = loader.load();
            ChangeStoreController changeStoreController = loader.getController();
            changeStoreController.getName(lWelcome.getText());
            Stage infoStage = new Stage();
            infoStage.initStyle(StageStyle.UNDECORATED);
            infoStage.setScene(new Scene(root, 380, 400));
            infoStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void loadData(String storeNew){

        String store = storeNew;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String getCategory = "SELECT * FROM items WHERE store = '" + store + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet getCat = statement.executeQuery(getCategory);

            while (getCat.next()) {
                addCat = getCat.getString("catagory");

                if(!searchCat.getItems().contains(addCat)) {
                    searchCat.getItems().add(addCat);
                }
            }


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void saleTable(String storeNew){
        String storeN = storeNew;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM items WHERE sale = 'TRUE' AND store = '" + storeN + "'");

            while(rs.next()){
                saleT.add(new sale(rs.getString("name"),rs.getString("catagory"),rs.getString("size"),rs.getString("price"),rs.getString("avalibilty"),rs.getString("isle"),rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        saTName.setCellValueFactory(new PropertyValueFactory<sale,String>("name"));
        saTCat.setCellValueFactory(new PropertyValueFactory<sale,String>("cat"));
        saTSize.setCellValueFactory(new PropertyValueFactory<sale,String>("size"));
        saTPrice.setCellValueFactory(new PropertyValueFactory<sale,String>("price"));
        saTAva.setCellValueFactory(new PropertyValueFactory<sale,String>("ava"));
        saTIsle.setCellValueFactory(new PropertyValueFactory<sale,String>("isle"));
        saTD.setCellValueFactory(new PropertyValueFactory<sale,String>("d"));
        saleTable.setItems(saleT);
    }


    public void searchB(ActionEvent event){
        if(searchName.getText().isEmpty()){
            System.out.println("EMPTY");
        }
        else{
            searchTable.getItems().clear();
            String searchN = searchName.getText();
            String storeNew = lStore.getText();
            searchTable(storeNew, searchN);
        }
    }

    public void catB(ActionEvent event){
        if(searchCat.getValue() == null){
            System.out.println("EMPTY");
        }
        else{
            searchTable.getItems().clear();
            String searchC = searchCat.getValue();
            String storeNew = lStore.getText();
            searchTable2(storeNew, searchC);
        }
    }
    public void searchTable(String storeNew, String searchN){
        String storeN = storeNew;
        String newSearch = searchN;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM items WHERE store = '" + storeN + "' AND name = '" + newSearch + "'");

            while(rs.next()){
                searchT.add(new search(rs.getString("name"),rs.getString("catagory"),rs.getString("size"),rs.getString("price"),rs.getString("avalibilty"),rs.getString("isle"),rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        srTName.setCellValueFactory(new PropertyValueFactory<search,String>("name"));
        srTCat.setCellValueFactory(new PropertyValueFactory<search,String>("cat"));
        srTSize.setCellValueFactory(new PropertyValueFactory<search,String>("size"));
        srTPrice.setCellValueFactory(new PropertyValueFactory<search,String>("price"));
        srTAva.setCellValueFactory(new PropertyValueFactory<search,String>("ava"));
        srTIsle.setCellValueFactory(new PropertyValueFactory<search,String>("isle"));
        srTD.setCellValueFactory(new PropertyValueFactory<search,String>("d"));
        searchTable.setItems(searchT);
    }

    public void searchTable2(String storeNew, String searchN){
        String storeN = storeNew;
        String newSearch = searchN;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM items WHERE store = '" + storeN + "' AND catagory = '" + newSearch + "'");

            while(rs.next()){
                searchT.add(new search(rs.getString("name"),rs.getString("catagory"),rs.getString("size"),rs.getString("price"),rs.getString("avalibilty"),rs.getString("isle"),rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        srTName.setCellValueFactory(new PropertyValueFactory<search,String>("name"));
        srTCat.setCellValueFactory(new PropertyValueFactory<search,String>("cat"));
        srTSize.setCellValueFactory(new PropertyValueFactory<search,String>("size"));
        srTPrice.setCellValueFactory(new PropertyValueFactory<search,String>("price"));
        srTAva.setCellValueFactory(new PropertyValueFactory<search,String>("ava"));
        srTIsle.setCellValueFactory(new PropertyValueFactory<search,String>("isle"));
        srTD.setCellValueFactory(new PropertyValueFactory<search,String>("d"));
        searchTable.setItems(searchT);
    }
    @FXML
    private void addSelectSearch(){
        ObservableList<search> add;
        add = searchTable.getSelectionModel().getSelectedItems();

        String addName = add.get(0).getName();
        String addIsle = add.get(0).getIsle();
        String addPrice = add.get(0).getPrice();

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String updateCart = "INSERT INTO cart(user,item,isle,price,store) VALUES ('";
        String updateCart2 = lWelcome.getText() + "','" + addName + "','" + addIsle + "','" + addPrice + "','" + lStore.getText() + "')";
        String uCart = updateCart + updateCart2;
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(uCart);
        }catch(Exception e){
            e.printStackTrace();
        }
        cart.getItems().clear();
        String searchC = lWelcome.getText();
        String storeNew = lStore.getText();
        cart(storeNew, searchC);

    }

    @FXML
    private void addSelectSale(){
        ObservableList<sale> add;
        add = saleTable.getSelectionModel().getSelectedItems();

        String addName = add.get(0).getName();
        String addIsle = add.get(0).getIsle();
        String addPrice = add.get(0).getPrice();

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String updateCart = "INSERT INTO cart(user,item,isle,price,store) VALUES ('";
        String updateCart2 = lWelcome.getText() + "','" + addName + "','" + addIsle + "','" + addPrice + "','" + lStore.getText() + "')";
        String uCart = updateCart + updateCart2;
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(uCart);
        }catch(Exception e){
            e.printStackTrace();
        }
        cart.getItems().clear();
        String searchC = lWelcome.getText();
        String storeNew = lStore.getText();
        cart(storeNew, searchC);
    }

    @FXML
    private void removeItem(){
        ObservableList<cartView> add;
        add = cart.getSelectionModel().getSelectedItems();

        String addName = add.get(0).getName();
        String addIsle = add.get(0).getIsle();
        String addPrice = add.get(0).getPrice();

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String updateCart = "DELETE FROM cart WHERE item = '" + addName + "' AND isle = '" + addIsle + "' AND price = '" + addPrice + "' limit 1";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateCart);
        }catch(Exception e){
            e.printStackTrace();
        }
        cart.getItems().clear();
        String searchC = lWelcome.getText();
        String storeNew = lStore.getText();
        cart(storeNew, searchC);
    }

    public void cart(String storeNew, String user){

        String storeN = storeNew;
        String newUser = user;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM cart WHERE store = '" + storeN + "' AND user = '" + newUser + "'");

            while(rs.next()){
                cartT.add(new cartView(rs.getString("item"),rs.getString("isle"), rs.getString("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cName.setCellValueFactory(new PropertyValueFactory<cartView,String>("name"));
        cIsle.setCellValueFactory(new PropertyValueFactory<cartView,String>("isle"));
        cPrice.setCellValueFactory(new PropertyValueFactory<cartView,String>("price"));
        cart.setItems(cartT);
    }

}




