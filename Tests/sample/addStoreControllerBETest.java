package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class addStoreControllerBETest {

    @Test
    public void addStore() {
        addStoreControllerBE nC = new addStoreControllerBE();
        String expected = "Store added successfully";
        String actual = nC.addStore("7:00am", "9:00pm", "Apples R Us");
    }
    @Test
    public void addStore2() {
        addStoreControllerBE nC = new addStoreControllerBE();
        String expected = "Item already exists in this store";
        String actual = nC.addStore("7:00am", "9:00pm", "Yorkdale");
    }
}