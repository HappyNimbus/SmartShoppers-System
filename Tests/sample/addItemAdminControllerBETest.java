package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class addItemAdminControllerBETest {

    @Test
    public void addNewItemAdmin() {
        addItemAdminControllerBE nC = new addItemAdminControllerBE();
        String expected =  "Item added successfully";
        String actual = nC.addNewItem("Clothes", "6.99", "Shorts", "5", "3", "4", "Yorkdale", "D", "Plain Shorts");
        assertEquals(expected, actual);
    }
    @Test
    public void addNewItemAdmin3() {
        addItemAdminControllerBE nC = new addItemAdminControllerBE();
        String expected =  "Item added successfully";
        String actual = nC.addNewItem("Clothes", "2.99", "Socks", "5", "3", "4", "Center", "D", "Plain Socks");
        assertEquals(expected, actual);
    }
    @Test
    public void addNewItemAdmin2() {
        addItemAdminControllerBE nC = new addItemAdminControllerBE();
        String expected =  "Item already exists in this store";
        String actual = nC.addNewItem("Clothes", "6.99", "T-Shirt", "4", "1", "3", "Yorkdale", "D", "Plain T-Shirt");
        assertEquals(expected, actual);
    }
}