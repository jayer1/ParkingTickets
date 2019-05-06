package jayer1.u2.parkingapp;

/**
* This is the Calculator interface used in conjunction with the below classes as a strategy design pattern
* It works with the CalculationType class to determine with 
*
*/
public interface Calculates {
    
    double calculate();
    
    
}


class MinMax implements Calculates{
    
    private int elapsedHours;
    
    public MinMax(int elapsedHours){
        this.elapsedHours = elapsedHours;
            
        }
    
    
    @Override
    public double calculate(){
        double amount = 0;
        
        if (elapsedHours <= 3) {
            amount = 5;
            //System.out.println("Amount is at minimum fee = " + amount);
        } else if (elapsedHours > 3 && elapsedHours <= 24) {
            amount = 5 + (elapsedHours - 3);
            //System.out.println("Elapsed hours = " + elapsedHours);
            //System.out.println("amount = 5 + " + (elapsedHours - THREE));
            //System.out.println("Amount (not min fee) = " + amount);
            if (amount > 15) {
                amount = 15;
            }
        }
        
        return amount;

        }

}

class SpecialEvent implements Calculates{

    @Override
    public double calculate() {
        return 20.00;
    }
    
}

class LostTicket implements Calculates{

    @Override
    public double calculate() {
        return 25.00;
    }
    
}

