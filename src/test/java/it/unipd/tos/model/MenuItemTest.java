////////////////////////////////////////////////////////////////////
// [CESARE] [OMODEI] [1187460]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MenuItemTest {
    private MenuItem item_test;

    @Before
    public void testBefore() {
        item_test=new MenuItem(ItemType.Budini,"Cioccolato",4);

    }

    @Test
    public void testItemType() {
        assertEquals(ItemType.Budini,item_test.getType());
    }

    @Test
    public void testName() {
        assertEquals("Cioccolato",item_test.getName());
    }

    @Test
    public void testPrice() {
        assertEquals(4,item_test.getPrice(),0);
    }
}