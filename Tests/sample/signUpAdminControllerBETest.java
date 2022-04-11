package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class signUpAdminControllerBETest {

    @Test
    public void registerAdmin() {
        signUpAdminControllerBE nC = new signUpAdminControllerBE();
        String expected = "Manager Registered Successfully";
        String actual = nC.registerAdmin("Marry", "Loo", "ml", "1", "1", "Manager", "Center", "111");
        assertEquals(expected, actual);
    }
    @Test
    public void registerAdmin2() {
        signUpAdminControllerBE nC = new signUpAdminControllerBE();
        String expected = "Admin Registered Successfully";
        String actual = nC.registerAdmin("Marry", "Loo", "mlAdmin", "1", "1", "Manager", "Admin", "101");
        assertEquals(expected, actual);
    }
}