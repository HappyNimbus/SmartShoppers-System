package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class removeStoreControllerBETest {

    @Test
    public void remove() {
        removeStoreControllerBE nC = new removeStoreControllerBE();
        String expected = "Store Removed";
        String actual = nC.remove("Apples R Us");
        assertEquals(expected, actual);
    }
    @Test
    public void remove2() {
        removeStoreControllerBE nC = new removeStoreControllerBE();
        String expected = "Information is missing";
        String actual = nC.remove("");
        assertEquals(expected, actual);
    }
}