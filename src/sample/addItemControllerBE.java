package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class addItemControllerBE {
    public static String addNewItem(String catagory, String price, String name, String L, String W, String H, String store, String isle, String description){

        String msg = "";
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM items WHERE name = '" + name + "' AND store = '" + store + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    msg = "Item already exists in this store";
                } else {
                    String insertFields = "INSERT INTO items (catagory, price, avalibilty, name, size, sale, store, isle, description) VALUES ('";
                    String insertValues = catagory + "','" + price + "','" + "TRUE" + "','" + name + "','" + L + "x" + W + "x" + H + "','" + "FALSE" + "','" + store + "','" + isle + "','" + description + "')";
                    String insertToRegister = insertFields + insertValues;

                    try {
                        Statement statement2 = connectDB.createStatement();
                        statement2.executeUpdate(insertToRegister);
                        msg = "Item added successfully";
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }
}
