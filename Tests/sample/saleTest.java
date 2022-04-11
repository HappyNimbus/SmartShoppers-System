package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class saleTest {

    @Test
    public void getName() {
        sale nC = new sale("Apple","Fruit", "1x1x1", "0.59", "TRUE", "A", "Its an apple");
        assertEquals("Apple", nC.getName());
    }

    @Test
    public void getCat() {
        sale nC = new sale("Apple","Fruit", "1x1x1", "0.59", "TRUE", "A", "Its an apple");
        assertEquals("Fruit", nC.getCat());
    }

    @Test
    public void getSize() {
        sale nC = new sale("Apple","Fruit", "1x1x1", "0.59", "TRUE", "A", "Its an apple");
        assertEquals("1x1x1", nC.getSize());
    }

    @Test
    public void getPrice() {
        sale nC = new sale("Apple","Fruit", "1x1x1", "0.59", "TRUE", "A", "Its an apple");
        assertEquals("0.59", nC.getPrice());
    }

    @Test
    public void getAva() {
        sale nC = new sale("Apple","Fruit", "1x1x1", "0.59", "TRUE", "A", "Its an apple");
        assertEquals("TRUE", nC.getAva());
    }

    @Test
    public void getIsle() {
        sale nC = new sale("Apple","Fruit", "1x1x1", "0.59", "TRUE", "A", "Its an apple");
        assertEquals("A", nC.getIsle());
    }

    @Test
    public void getD() {
        sale nC = new sale("Apple","Fruit", "1x1x1", "0.59", "TRUE", "A", "Its an apple");
        assertEquals("Its an apple", nC.getD());
    }
}