package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class removeItemAdminControllerBETest {

    @Test
    public void remove() {
        removeItemAdminControllerBE nC = new removeItemAdminControllerBE();
        String expected = "Item Removed";
        String actual = nC.remove("Shorts", "Yorkdale");
        assertEquals(expected, actual);
    }
    @Test
    public void remove4() {
        removeItemAdminControllerBE nC = new removeItemAdminControllerBE();
        String expected = "Item Removed";
        String actual = nC.remove("Charger", "BeachST");
        assertEquals(expected, actual);
    }
    @Test
    public void remove2() {
        removeItemAdminControllerBE nC = new removeItemAdminControllerBE();
        String expected = "Information is missing";
        String actual = nC.remove("", "Yorkdale");
        assertEquals(expected, actual);
    }
    @Test
    public void remove3() {
        removeItemAdminControllerBE nC = new removeItemAdminControllerBE();
        String expected = "No store was selected";
        String actual = nC.remove("Shorts", "");
        assertEquals(expected, actual);
    }
}