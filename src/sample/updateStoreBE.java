package sample;

import java.sql.Connection;
import java.sql.Statement;

public class updateStoreBE {
    public static String updateOpen(String newOpen, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";
        String newChange = "UPDATE store SET open = '" + newOpen + "' WHERE store = '" + store + "'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(newChange);
            msg = "Opening Hours Change Successful";

        }catch(Exception e){
            e.printStackTrace();
        }
    return msg;
    }

    public static String updateClose(String newClose, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";
        String newChange = "UPDATE store SET close = '" + newClose + "' WHERE store = '" + store + "'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(newChange);
            msg = "Closing Hours Change Successful";

        }catch(Exception e){
            e.printStackTrace();
        }
    return msg;
    }

    public static String updateAva(String newAva, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String msg = "";
        String newChange = "UPDATE store SET avalibility = '" + newAva + "' WHERE store = '" + store + "'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(newChange);
            msg = "Availability Change Successful";

        }catch(Exception e){
            e.printStackTrace();
        }
        return msg;
    }
}
