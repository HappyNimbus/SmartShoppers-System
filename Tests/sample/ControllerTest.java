package sample;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class ControllerTest {

    @Test
    public void authentication() {
        Controller nC = new Controller();
        String expected = "SELECT authentication FROM users WHERE username = 'max'";
        String actual = nC.authentication("max");
        assertEquals(expected, actual);
    }

    @Test
    public void validateLogin() {
        Controller nC = new Controller();
        String expected = "Login Success";
        String user = "max";
        String pass = "1";
        String actual = nC.validateLogin(user,pass);
        assertEquals(expected, actual);
    }
    @Test
    public void validateLogin2() {
        Controller nC = new Controller();
        String expected = "Invalid login. Please try again";
        String user = "dogman";
        String pass = "dogmeat";
        String actual = nC.validateLogin(user,pass);
        assertEquals(expected, actual);
    }

}