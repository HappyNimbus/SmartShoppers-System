package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class cartViewTest {

    @Test
    public void getName() {
        cartView nC = new cartView("Apple", "A", "1.99");
        assertEquals("Apple", nC.getName());
    }

    @Test
    public void getIsle() {
        cartView nC = new cartView("Apple", "A", "1.99");
        assertEquals("A", nC.getIsle());
    }

    @Test
    public void getPrice() {
        cartView nC = new cartView("Apple", "A", "1.99");
        assertEquals("1.99", nC.getPrice());
    }
}