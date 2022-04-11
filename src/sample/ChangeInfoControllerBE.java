package sample;

import java.sql.Connection;
import java.sql.Statement;

public class ChangeInfoControllerBE {
    public static String changeUserInfo(String changedUser, String changedPass, String originalUser) {

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String msg = "";

        if(!changedUser.equals("") && !changedPass.equals("")){

            String userChange = "UPDATE users SET username = '" + changedUser + "' WHERE username = '" + originalUser + "'";
            String passChange = "UPDATE users SET password = '" + changedPass + "' WHERE username = '" + originalUser + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(passChange);
                statement.executeUpdate(userChange);
                msg = "Change Successful";


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            return msg;
        }

        else if (!changedUser.equals("")) {

            String userChange = "UPDATE users SET username = '" + changedUser + "' WHERE username = '" + originalUser + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(userChange);
                msg = "Username change Successful";


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            return msg;
        }
        else if(!changedPass.equals("")){
            String passChange = "UPDATE users SET password = '" + changedPass + "' WHERE username = '" + originalUser + "'";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(passChange);
                msg = "Password Change Successful";

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            return msg;
        }

        return msg;
    }


    public static String deleteAccount(String originalUser){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String msg = "";
        String deleteUser = "DELETE FROM users WHERE username = '" + originalUser + "'";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteUser);
            msg = "Deleted";

        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return msg;
    }

}
