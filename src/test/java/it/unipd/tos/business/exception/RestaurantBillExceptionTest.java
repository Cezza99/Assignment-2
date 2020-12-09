////////////////////////////////////////////////////////////////////
// [CESARE] [OMODEI] [1187460]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RestaurantBillExceptionTest{


    @Test
    public void testRestaurantBillException() {
        assertEquals("Test failed",new RestaurantBillException("Test failed").getMessage());
    }
}