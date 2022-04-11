package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class removeItemControllerBETest {

    @Test
    public void remove() {
        removeItemControllerBE nC = new removeItemControllerBE();
        String expected = "Item Removed";
        String actual = nC.remove("Socks", "Center");
        assertEquals(expected, actual);
    }
    @Test
    public void remove2() {
        removeItemAdminControllerBE nC = new removeItemAdminControllerBE();
        String expected = "Information is missing";
        String actual = nC.remove("", "Center");
        assertEquals(expected, actual);
    }
}