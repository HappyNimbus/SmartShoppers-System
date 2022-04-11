package sample;

import java.sql.Connection;
import java.sql.Statement;

public class signUpControllerBE {
    public static String registerUser(String firstname, String lastname, String username, String password, String password2, String authentication, String storepref) throws NullPointerException {
        String msg = "";
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        if (password.equals(password2)) {
            String insertFields = "INSERT INTO users (firstname, lastname, username, password, authentication, storepref) VALUES ('";
            String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "','" + authentication + "','" + storepref + "')";
            String insertToRegister = insertFields + insertValues;

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                msg = "User registered successfully";

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
        else{
            msg = "Password does not match";
        }
        return msg;
    }

}
