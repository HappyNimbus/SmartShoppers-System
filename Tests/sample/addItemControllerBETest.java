package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class addItemControllerBETest {

    @Test
    public void addNewItem() {
        addItemAdminControllerBE nC = new addItemAdminControllerBE();
        String expected =  "Item added successfully";
        String actual = nC.addNewItem("Electronic", "10.99", "Charger", "10", "1", "1", "BeachST", "D", "This is a charger");
        assertEquals(expected, actual);
    }
    @Test
    public void addNewItemAdmin2() {
        addItemControllerBE nC = new addItemControllerBE();
        String expected =  "Item already exists in this store";
        String actual = nC.addNewItem("Bread", "6.99", "Moldy Bread", "4", "1", "3", "BeachST", "D", "Plain T-shirt");
        assertEquals(expected, actual);
    }
}