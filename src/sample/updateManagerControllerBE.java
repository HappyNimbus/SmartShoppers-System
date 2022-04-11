package sample;

import java.sql.Connection;
import java.sql.Statement;

public class updateManagerControllerBE {
    public static String updateFirstname(String first, String oldUser){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";
        String updateFirst = "UPDATE users SET firstname = '" + first + "' WHERE username = '" + oldUser + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFirst);
            msg = "Manager firstname update successfully";
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return msg;
    }
    public static String updateLast(String last, String oldUser){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";

        String updateFirst = "UPDATE users SET lastname = '" + last + "' WHERE username = '" + oldUser + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFirst);
            msg = "Manager lastname update successfully";
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return msg;
    }

    public static String updateUserName(String user, String oldUser){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";

        String updateFirst = "UPDATE users SET username = '" + user + "' WHERE username = '" + oldUser + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFirst);
            msg = "Manager username update successfully";
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return msg;
    }
    public static String updatePassword(String pass, String oldUser){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String msg = "";
        String updateFirst = "UPDATE users SET password = '" + pass + "' WHERE username = '" + oldUser + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFirst);
            msg = "Manager password update successfully";
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return msg;
    }
    public static String updateStore(String store, String oldUser){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String msg = "";
        String updateFirst = "UPDATE users SET storePref = '" + store + "' WHERE username = '" + oldUser + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFirst);
            msg = "Manager store update successfully";
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return msg;
    }

    public static String create(String firstname, String lastname, String username, String password, String store, String authentication){

        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";

        String insertFields = "INSERT INTO users (firstname, lastname, username, password, authentication, storepref) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "','" + authentication + "','" + store + "')";
        String insertToRegister = insertFields + insertValues;
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            msg = "Manager registered successfully";
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return msg;
    }
    public static String delete(String userDelete){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String msg = "";
        String deleteInput = "DELETE FROM users WHERE username = '" + userDelete + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteInput);
            msg = "Manager deleted successfully";
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return msg;
    }
}
