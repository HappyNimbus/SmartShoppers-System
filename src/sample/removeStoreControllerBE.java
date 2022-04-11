package sample;

import java.sql.Connection;
import java.sql.Statement;

public class removeStoreControllerBE {
    public static String remove(String storeName){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";

        if(storeName.equals("")){
            msg = "Information is missing";
        }

        else {
            String deleteItem = "DELETE FROM store WHERE store = '" + storeName + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(deleteItem);
                msg = "Store Removed";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msg;
    }
}
