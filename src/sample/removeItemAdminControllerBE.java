package sample;

import java.sql.Connection;
import java.sql.Statement;

public class removeItemAdminControllerBE {
    public static String remove(String productName, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg="";

        if(store.equals("")){
            msg = "No store was selected";
        }
        else if(productName.equals("")){
            msg = "Information is missing";
        }

        else {
            String deleteItem = "DELETE FROM items WHERE name = '" + productName + "'" + "AND store = '" + store + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(deleteItem);
                msg = "Item Removed";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msg;
    }
}
