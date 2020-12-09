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

        if(itemsOrdered.size()>30) {
            throw new RestaurantBillException("Impossibile ordinare "
                    + "un numero maggiore di 30 elementi");
        }
        
        int counter_gelati=0;
        for(int i = 0; i < itemsOrdered.size(); i++) {
            if(itemsOrdered.get(i).getType()==ItemType.Gelati) {
                counter_gelati++;
            }
        }

        double sum = 0;
        double prezzoPieno=0;
        double minimoPrezzo=Double.MAX_VALUE;
        
        for (int i = 0; i < itemsOrdered.size(); i++) {
            MenuItem item = itemsOrdered.get(i);
            
            if(item.getType()==ItemType.Gelati) {
                prezzoPieno+=item.getPrice();
                minimoPrezzo=item.getPrice()<minimoPrezzo
                        ? item.getPrice() : minimoPrezzo;
            }
            else if(item.getType()==ItemType.Budini){
                prezzoPieno+=item.getPrice();
            }
            sum+=item.getPrice();
        }
        
        return calcSum(counter_gelati,minimoPrezzo,prezzoPieno,sum);
    }

    
    private double calcSum(double counter_gelati,
            double minimoPrezzo,double prezzoPieno,double sum) {
        
        if(counter_gelati>5) {
            sum-=(minimoPrezzo/2);
            prezzoPieno-=(minimoPrezzo/2);
        }
        
        if(prezzoPieno>50) {
            sum-=sum*0.1;
        }
        
        if(sum>0 && sum<10) {
            sum+=0.5;
        }
        
        return sum;
    }


}