////////////////////////////////////////////////////////////////////
// [CESARE] [OMODEI] [1187460]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;
import java.time.LocalTime;
import java.util.Random;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillImp implements TakeAwayBill {
    static int ordiniGratis = 10;
    static Random seed = new Random(1000);
    
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user
            ,LocalTime orderTime) 
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
        
        if(OrdineGratis(orderTime,user)) {
            return 0;
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
    
    private boolean OrdineGratis(LocalTime orderTime, User user) {

        if (orderTime.isAfter(LocalTime.of(17, 59, 59)) && 
                orderTime.isBefore(LocalTime.of(19, 00, 01))) {
            if (user.isMinorenne()) {
                if (ordiniGratis > 0) {

                    int x = seed.nextInt() & Integer.MAX_VALUE;
                    
                    if (x % 100 < 50) {
                        ordiniGratis--;
                        return true;
                    }
                }
            }
        } else {
            ordiniGratis = 10;
        }

        return false;

    }


}