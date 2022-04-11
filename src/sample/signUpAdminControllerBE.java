package sample;

import java.sql.Connection;
import java.sql.Statement;

public class signUpAdminControllerBE {
    public static String registerAdmin(String firstname, String lastname, String username, String password,String password2, String authentication, String store, String code){
        String msg = "";
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        if(code.equals("101")){
            authentication = "Admin";

            store = "ALL";
            String insertFields = "INSERT INTO users (firstname, lastname, username, password, authentication, storepref) VALUES ('";
            String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "','" + authentication + "','" + store + "')";
            String insertToRegister = insertFields + insertValues;
            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                msg = "Admin Registered Successfully";
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }

        }

        else if(code.equals("111")){
            authentication = "Manager";
            String insertFields = "INSERT INTO users (firstname, lastname, username, password, authentication, storepref) VALUES ('";
            String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "','" + authentication + "','" + store + "')";
            String insertToRegister = insertFields + insertValues;
            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                msg = "Manager Registered Successfully";
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
        return msg;
    }

}
