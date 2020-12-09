////////////////////////////////////////////////////////////////////
// [CESARE] [OMODEI] [1187460]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User test_user;

    @Before
    public void beforeTest() {
        test_user=new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13));

    }

    @Test
    public void idTest() {
        assertEquals("000001",test_user.getID());
    }

    @Test
    public void nameTest() {
        assertEquals("Cesare",test_user.getNome());
    }

    @Test
    public void cognomeTest() {
        assertEquals("Omodei",test_user.getCognome());
    }

    @Test
    public void dateTest() {
        assertEquals(LocalDate.of(1999, 4, 13),test_user.getDataNascita());
    }
} 