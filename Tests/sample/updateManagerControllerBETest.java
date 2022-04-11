package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class updateManagerControllerBETest {

    @Test
    public void updateFirstname() {
        updateManagerControllerBE nC = new updateManagerControllerBE();
        String expected = "Manager firstname update successfully";
        String actual = nC.updateFirstname("Yvon", "FishDish");
        assertEquals(expected, actual);
    }

    @Test
    public void updateLast() {
        updateManagerControllerBE nC = new updateManagerControllerBE();
        String expected = "Manager lastname update successfully";
        String actual = nC.updateLast("D", "FishDish");
        assertEquals(expected, actual);
    }

    @Test
    public void updateUserName() {
        updateManagerControllerBE nC = new updateManagerControllerBE();
        String expected = "Manager username update successfully";
        String actual = nC.updateUserName("fishdish", "FishDish");
        assertEquals(expected, actual);
    }
    @Test
    public void updateUserName2() {
        updateManagerControllerBE nC = new updateManagerControllerBE();
        String expected = "Manager username update successfully";
        String actual = nC.updateUserName("FishDish", "fishdish");
        assertEquals(expected, actual);
    }

    @Test
    public void updatePassword() {
        updateManagerControllerBE nC = new updateManagerControllerBE();
        String expected = "Manager password update successfully";
        String actual = nC.updatePassword("fish", "FishDish");
        assertEquals(expected, actual);
    }

    @Test
    public void updateStore() {
        updateManagerControllerBE nC = new updateManagerControllerBE();
        String expected = "Manager store update successfully";
        String actual = nC.updateStore("Center", "FishDish");
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        updateManagerControllerBE nC = new updateManagerControllerBE();
        String expected = "Manager registered successfully";
        String actual = nC.create("Jim", "Joe", "jj","5", "Corner", "MANAGER");
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        updateManagerControllerBE nC = new updateManagerControllerBE();
        String expected = "Manager deleted successfully";
        String actual = nC.delete("jj");
        assertEquals(expected, actual);
    }
    @Test
    public void delete2() {
        updateManagerControllerBE nC = new updateManagerControllerBE();
        String expected = "Manager deleted successfully";
        String actual = nC.delete("ml");
        assertEquals(expected, actual);
    }
}