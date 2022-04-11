package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeStoreControllerBETest {

    @Test
    public void changeLocationInfo() {
        ChangeStoreControllerBE nC = new ChangeStoreControllerBE();
        String expected = "Location changed";
        String actual = nC.changeLocationInfo("Center","max");
        assertEquals(expected, actual);
    }
    @Test
    public void changeLocationInfo2() {
        ChangeStoreControllerBE nC = new ChangeStoreControllerBE();
        String expected = "No location entered";
        String actual = nC.changeLocationInfo("","max");
        assertEquals(expected, actual);
    }
}