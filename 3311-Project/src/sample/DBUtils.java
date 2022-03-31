package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String password) {

        Parent root = null;

        if(username != null && password != null){

            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                loginController loginController = loader.getController();
                loginController.setUserInfo(username,password);
            } catch (IOException e){
                e.printStackTrace();
            }

        } else{
            try{
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();


    }

    public static void signUpUser(ActionEvent event, String username, String password, String email){

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUser = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/logininfo", "root", "root");
            psCheckUser = connection.prepareStatement( "SELECT * FROM userstest WHERE username = ?");
            psCheckUser.setString( 1, username);
            resultSet = psCheckUser.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username already exists.");
                alert.show();
            } else{
                psInsert = connection.prepareStatement("INSERT INTO userstest (userName, password, email, authority, storePref, firstname, lastname) VALUES (?, ?, ?)");
                psInsert.setString( 1, username);
                psInsert.setString( 2, password);
                psInsert.setString(3, email);
                //psInsert.setString(4, authority);
                //psInsert.setString( 5, storePref);
                //psInsert.setString(6, firstname);
                //psInsert.setString(7, lastname);
                //psInsert.executeUpdate();

                changeScene(event, "logged-in.fxml", "", username, password);

            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally{
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUser != null){
                try{
                    psCheckUser.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try{
                    psInsert.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try{
                    connection.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }


    }

    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/logininfo", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT password, email FROM userstest WHERE userName = ? ");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username or Password Incorrect");
                alert.show();

            } else{
                while(resultSet.next()) {
                    String retrivedPassword = resultSet.getString("password");
                    String retrivedAuth = resultSet.getString("email");

                    if (retrivedPassword.equals(password)){
                        changeScene(event, "logged-in.fxml", "", username, retrivedAuth);

                    } else{
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Username or Password Incorrect");
                        alert.show();
                    }
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        }
    }


