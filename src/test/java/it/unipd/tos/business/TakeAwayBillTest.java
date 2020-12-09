////////////////////////////////////////////////////////////////////
// [CESARE] [OMODEI] [1187460]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillTest {

    private TakeAwayBillImp tkw_bill;

    @Before  
    public void BeforeClass() {
        tkw_bill = new TakeAwayBillImp();
    }

    @Test
    public void testStdGetOrderPrice() {
        List<MenuItem> order = new ArrayList<MenuItem>();
        order.add(new MenuItem(ItemType.Budini,"Cioccolato",4));
        order.add(new MenuItem(ItemType.Gelati,"Pistacchio",3));
        order.add(new MenuItem(ItemType.Bevande,"Coca Cola",2)); 

        try {
            assertEquals(9, tkw_bill.getOrderPrice(order, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13))),0);
        } catch (RestaurantBillException ex) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }

    }

    @Test
    public void testEmptyOrderPrice() {
        List<MenuItem> empty_order = new ArrayList<MenuItem>();

        try {
            assertEquals(0, tkw_bill.getOrderPrice(empty_order, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13))),0);
        } catch (RestaurantBillException ex) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }

    }
    
    @Test
    public void testNoScontoGelati() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Amarena",4));
        list.add(new MenuItem(ItemType.Gelati,"Piastacchio",2));
        list.add(new MenuItem(ItemType.Gelati,"Limone",6)); 
        list.add(new MenuItem(ItemType.Gelati,"Fragola",4)); 
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",3));
        list.add(new MenuItem(ItemType.Budini,"Pinguino",5));

        try {
            assertEquals(24, tkw_bill.getOrderPrice(list, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13))),0);
        } catch (RestaurantBillException e) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }


    }

    public void testScontoGelati() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Amarena",4));
        list.add(new MenuItem(ItemType.Gelati,"Piastacchio",2));
        list.add(new MenuItem(ItemType.Gelati,"Limone",6)); 
        list.add(new MenuItem(ItemType.Gelati,"Fragola",4)); 
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",3));
        list.add(new MenuItem(ItemType.Gelati,"Nocciola",3));
        list.add(new MenuItem(ItemType.Budini,"Pinguino",5));

        try {
            assertEquals(26, tkw_bill.getOrderPrice(list, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13))),0);
        } catch (RestaurantBillException e) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }


    }


} 

    