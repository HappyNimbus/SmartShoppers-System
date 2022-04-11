package sample;

import java.sql.Connection;
import java.sql.Statement;

public class ChangeStoreControllerBE {
    public static String changeLocationInfo(String changedLocation, String originalUser) {

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";

        if(!changedLocation.equals("")){
            String userChange = "UPDATE users SET storepref = '" + changedLocation + "' WHERE username = '" + originalUser + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(userChange);
                msg = "Location changed";


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
        else{
            msg = "No location entered";
        }
        return msg;
    }
}
