package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class updateItemBETest {

    @Test
    public void updateName() {
        updateItemBE nC = new updateItemBE();
        String expected = "Name Change Successful";
        String actual = nC.updateName("POMME", "Apple", "Yorkdale");
        assertEquals(expected,actual);
    }
    @Test
    public void updateName2() {
        updateItemBE nC = new updateItemBE();
        String expected = "Name Change Successful";
        String actual = nC.updateName("Apple", "POMME", "Yorkdale");
        assertEquals(expected,actual);
    }
    @Test
    public void updateName3() {
        updateItemBE nC = new updateItemBE();
        String expected = "Fill in Name to update";
        String actual = nC.updateName("", "POMME", "Yorkdale");
        assertEquals(expected,actual);
    }

    @Test
    public void updateCat() {
        updateItemBE nC = new updateItemBE();
        String expected = "Category Change Successful";
        String actual = nC.updateCat("Veg", "Apple", "Yorkdale");
        assertEquals(expected,actual);
    }
    @Test
    public void updateCat2() {
        updateItemBE nC = new updateItemBE();
        String expected = "Fill in catagory to update";
        String actual = nC.updateCat("", "Apple", "Yorkdale");
        assertEquals(expected,actual);
    }

    @Test
    public void updatePrice() {
        updateItemBE nC = new updateItemBE();
        String expected = "Price Change Successful";
        String actual = nC.updatePrice("0.15", "Apple", "Yorkdale");
        assertEquals(expected,actual);
    }


    @Test
    public void updateSize() {
        updateItemBE nC = new updateItemBE();
        String expected = "Size Change Successful";
        String actual = nC.updateSize("1","2","1", "Apple", "Yorkdale");
        assertEquals(expected,actual);
    }

    @Test
    public void updateIsle() {
        updateItemBE nC = new updateItemBE();
        String expected = "Isle Change Successful";
        String actual = nC.updateIsle("C", "Apple", "Yorkdale");
        assertEquals(expected,actual);
    }

    @Test
    public void updateDesc() {
        updateItemBE nC = new updateItemBE();
        String expected = "Description Change Successful";
        String actual = nC.updateDesc("Its a round apple.", "Apple", "Yorkdale");
        assertEquals(expected,actual);
    }

    @Test
    public void updateAva() {
        updateItemBE nC = new updateItemBE();
        String expected = "Availability Change Successful";
        String actual = updateItemBE.updateAva("FALSE", "Apple", "Yorkdale");
        assertEquals(expected,actual);
    }

    @Test
    public void updateSale() {
        updateItemBE nC = new updateItemBE();
        String expected = "Sale Change Successful";
        String actual = nC.updateSale("FALSE", "Apple", "Yorkdale");
        assertEquals(expected,actual);
    }
}