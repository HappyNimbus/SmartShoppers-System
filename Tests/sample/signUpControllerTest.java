package sample;

import javafx.fxml.FXML;
import org.junit.Test;

import static org.junit.Assert.*;

public class signUpControllerTest {

    @Test
    public void registerUser() {
        signUpControllerBE nC = new signUpControllerBE();
        String expected = "User registered successfully";
        String actual = nC.registerUser("John", "Doe", "Happy12", "123", "123", "user", "Yorkdale");
        assertEquals(expected, actual);
    }

    @Test
    public void registerUser2() {
        signUpControllerBE nC = new signUpControllerBE();
        String expected = "Password does not match";
        String actual = nC.registerUser("John", "Doe", "Happy12", "123", "1234", "user", "Yorkdale");
        assertEquals(expected, actual);
    }
}