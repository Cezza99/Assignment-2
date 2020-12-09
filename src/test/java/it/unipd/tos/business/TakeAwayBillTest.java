////////////////////////////////////////////////////////////////////
// [CESARE] [OMODEI] [1187460]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
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
        TakeAwayBillImp.ordiniGratis=10;
    }

    @Test
    public void testStdGetOrderPrice() {
        List<MenuItem> order = new ArrayList<MenuItem>();
        order.add(new MenuItem(ItemType.Budini,"Cioccolato",4));
        order.add(new MenuItem(ItemType.Gelati,"Pistacchio",3));
        order.add(new MenuItem(ItemType.Bevande,"Coca Cola",2)); 

        try {
            assertEquals(9.5, tkw_bill.getOrderPrice(order, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13)), LocalTime.of(17,0)),0);
        } catch (RestaurantBillException ex) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }

    }

    @Test
    public void testEmptyOrderPrice() {
        List<MenuItem> empty_order = new ArrayList<MenuItem>();

        try {
            assertEquals(0, tkw_bill.getOrderPrice(empty_order, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13)),LocalTime.of(17,0)),0);
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
            assertEquals(24, tkw_bill.getOrderPrice(list, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13)),LocalTime.of(17,0)),0);
        } catch (RestaurantBillException e) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }


    }
    
    @Test
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
            assertEquals(26, tkw_bill.getOrderPrice(list, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13)),LocalTime.of(17,0)),0);
        } catch (RestaurantBillException e) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }


    }
    
    @Test
    public void testScontoGelati_ScontoTotale() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Amarena",10));
        list.add(new MenuItem(ItemType.Gelati,"Limone",10));
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",10)); 
        list.add(new MenuItem(ItemType.Gelati,"Pistacchio",6)); 
        list.add(new MenuItem(ItemType.Gelati,"Nocciola",10));   
        list.add(new MenuItem(ItemType.Gelati,"Fragola",10));
        list.add(new MenuItem(ItemType.Budini,"Pinguino",10));

        try {
            assertEquals(56.7, tkw_bill.getOrderPrice(list, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13)),LocalTime.of(17,0)),0);
        } catch (RestaurantBillException e) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }


    }

    @Test
    public void testScontoTotale() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",10));
        list.add(new MenuItem(ItemType.Gelati,"Crema",10));
        list.add(new MenuItem(ItemType.Gelati,"Limone",10));
        list.add(new MenuItem(ItemType.Gelati,"Fragola",10));
        list.add(new MenuItem(ItemType.Budini,"Pinguino",20));

        try {
            assertEquals(54, tkw_bill.getOrderPrice(list, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13)),LocalTime.of(17,0)),0);
        } catch (RestaurantBillException e) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }


    }
    
    @Test(expected = RestaurantBillException.class)
    public void testOrdine30Elementi() throws RestaurantBillException{
        List<MenuItem> list = new ArrayList<MenuItem>();
        for(int i = 0; i < 31; i++) {
            list.add(new MenuItem(ItemType.Budini, "Pinguino", 2));
        }
        tkw_bill.getOrderPrice(list,new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13)),LocalTime.of(17,0));
    }
    
    @Test
    public void testContoMinore10() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",2));
        list.add(new MenuItem(ItemType.Gelati,"Fragola",2));
        list.add(new MenuItem(ItemType.Budini,"Pinguino",3));
        list.add(new MenuItem(ItemType.Bevande,"Coca Cola",2));

        try {
            assertEquals(9.5, tkw_bill.getOrderPrice(list, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13)),LocalTime.of(17,0)),0);
        } catch (RestaurantBillException e) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }

    }

    @Test
    public void testContoMinore10_SeiGelati() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",1));
        list.add(new MenuItem(ItemType.Gelati,"Fragola",1));
        list.add(new MenuItem(ItemType.Gelati,"Limone",1));
        list.add(new MenuItem(ItemType.Gelati,"Gianduia",1));
        list.add(new MenuItem(ItemType.Gelati,"Pistacchio",1));
        list.add(new MenuItem(ItemType.Gelati,"Nocciola",1));

        try {
            assertEquals(6, tkw_bill.getOrderPrice(list, new User("000001","Cesare","Omodei",LocalDate.of(1999,4,13)),LocalTime.of(17,0)),0);
        } catch (RestaurantBillException e) {
            // TODO Auto-generated catch block
            fail("Test failed");

        }

    }
    
    @Test
    public void testOrdineGratis() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",10));
        Random seed= new Random(1000);
        double sum=0;

        for(int i=0;i<50;i++) {
            try {
                sum+= tkw_bill.getOrderPrice(list,new User("000001","Cesare","Omodei",LocalDate.now()), LocalTime.of(18, 15));
            } catch (RestaurantBillException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        Vector<Integer> test = new Vector<Integer>(100);




        for (int i=0;i<100;++i) {
            int x=seed.nextInt() & Integer.MAX_VALUE;
            test.add(x%100);
        }

        double tot=0;
        int gratis=0;
        for (int i=0; i<50;++i) {

            if(test.elementAt(i)<50 && gratis<10) {     
                gratis++;
                tot+=0;
            } else tot+=10;

        }

        assertEquals(tot,sum,0);


    }


    @Test
    public void testOrdineGratis_LimiteMassimo() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",10));
        double sum=0;

        for(int i=0;i<50;i++) {
            try {
                sum+= tkw_bill.getOrderPrice(list,new User("000001","Cesare","Omodei",LocalDate.now()), LocalTime.of(19,00,01));
            } catch (RestaurantBillException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        assertEquals(500,sum,0);


    }

    @Test
    public void testOrdiniGratis_LimiteMinimo() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",10));       
        double sum=0;

        for(int i=0;i<50;i++) {
            try {
                sum+= tkw_bill.getOrderPrice(list,new User("000001","Cesare","Omodei",LocalDate.now()), LocalTime.of(17,59,59));
            } catch (RestaurantBillException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        assertEquals(500,sum,0);


    }

    @Test
    public void testOrdiniGratisLimiteMComp() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",10));       
        double sum=0;

        for(int i=0;i<50;i++) {
            try {
                sum+= tkw_bill.getOrderPrice(list,new User("000001","Cesare","Omodei",LocalDate.now()), LocalTime.of(18,00,00));
            } catch (RestaurantBillException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        assertEquals(400,sum,0);


    }

    @Test
    public void testOrdiniGratisLimiteSComp() {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem(ItemType.Gelati,"Cioccolato",10));       
        double sum=0;

        for(int i=0;i<50;i++) {
            try {
                sum+= tkw_bill.getOrderPrice(list,new User("000001","Cesare","Omodei",LocalDate.now()), LocalTime.of(19,00,00));
            } catch (RestaurantBillException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        assertEquals(400,sum,0);


    }
} 

    