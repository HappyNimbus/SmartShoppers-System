package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeInfoControllerBETest {

    @Test
    public void changeUserInfo() {
        ChangeInfoControllerBE nC = new ChangeInfoControllerBE();
        String expected = "Change Successful";
        String actual = nC.changeUserInfo("Happy", "12","Happy12");
        assertEquals(expected, actual);
    }
    @Test
    public void changeUserInfo2() {
        ChangeInfoControllerBE nC = new ChangeInfoControllerBE();
        String expected = "Password Change Successful";
        String actual = nC.changeUserInfo("", "123","Happy");
        assertEquals(expected, actual);
    }
    @Test
    public void changeUserInfo3() {
        ChangeInfoControllerBE nC = new ChangeInfoControllerBE();
        String expected = "Username change Successful";
        String actual = nC.changeUserInfo("Happy12", "","Happy");
        assertEquals(expected, actual);
    }


    @Test
    public void deleteAccount() {
        ChangeInfoControllerBE nC = new ChangeInfoControllerBE();
        String expected = "Deleted";
        String actual = nC.deleteAccount("Happy12");
        assertEquals(expected, actual);
    }
    @Test
    public void deleteAccount2() {
        ChangeInfoControllerBE nC = new ChangeInfoControllerBE();
        String expected = "Deleted";
        String actual = nC.deleteAccount("mlAdmin");
        assertEquals(expected, actual);
    }
}