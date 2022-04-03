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

    public Connection dbLink;

    public Connection getConnection(){

        String dbName = "info";
        String dbUser = "root";
        String dbPass = "root";
        String url = "jdbc:mysql://localhost/" + dbName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbLink = DriverManager.getConnection(url,dbUser,dbPass);
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return dbLink;
    }
}

