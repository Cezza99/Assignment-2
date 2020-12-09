////////////////////////////////////////////////////////////////////
// [CESARE] [OMODEI] [1187460]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;
import java.time.LocalTime;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public interface TakeAwayBill {

    double getOrderPrice(List<MenuItem> itemsOrdered, User user
            ,LocalTime orderTime) 
            throws RestaurantBillException;

}