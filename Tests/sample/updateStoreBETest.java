package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class updateStoreBETest {

    @Test
    public void updateOpen() {
        updateStoreBE nC = new updateStoreBE();
        String expected = "Opening Hours Change Successful";
        String actual = nC.updateOpen("6:00am", "BeachST");
        assertEquals(expected,actual);
    }

    @Test
    public void updateClose() {
        updateStoreBE nC = new updateStoreBE();
        String expected = "Closing Hours Change Successful";
        String actual = nC.updateClose("9:41am", "BeachST");
        assertEquals(expected,actual);
    }

    @Test
    public void updateAva() {
        updateStoreBE nC = new updateStoreBE();
        String expected = "Availability Change Successful";
        String actual = nC.updateAva("FALSE", "BeachST");
        assertEquals(expected,actual);
    }
}