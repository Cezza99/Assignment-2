////////////////////////////////////////////////////////////////////
// [CESARE] [OMODEI] [1187460]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillImp implements TakeAwayBill {

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) 
            throws RestaurantBillException {

        int counter_gelati=0;
        for(int i = 0; i < itemsOrdered.size(); i++) {
            if(itemsOrdered.get(i).getType()==ItemType.Gelati) {
                counter_gelati++;
            }
        }

        double sum = 0;
        double minimoPrezzo=Double.MAX_VALUE;
        
        for (int i = 0; i < itemsOrdered.size(); i++) {
            MenuItem item = itemsOrdered.get(i);
            if(counter_gelati>5) {
                if(item.getType()==ItemType.Gelati) {
                    minimoPrezzo=item.getPrice()<minimoPrezzo
                            ? item.getPrice() : minimoPrezzo;
                }
            }
            sum+=item.getPrice();
        }
        
        if(counter_gelati>5) {
            sum-=(minimoPrezzo/2);
        }
                
        return sum;
    }
}