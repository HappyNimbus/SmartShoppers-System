package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class addStoreControllerBE {
    public static String addStore(String open, String close, String store) {
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";

        String verifyLogin = "SELECT count(1) FROM store WHERE store = '" + store + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    msg = "Store already exists";
                }

                else {
                    String insertFields = "INSERT INTO store (open, close, avalibility, store) VALUES ('";
                    String insertValues = open + "','" + close + "','" + "TRUE" + "','" + store + "')";
                    String insert = insertFields + insertValues;

                    try {
                        Statement statement2 = connectDB.createStatement();
                        statement2.executeUpdate(insert);
                        msg = "Store added successfully";

                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
