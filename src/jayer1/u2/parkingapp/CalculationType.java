package jayer1.u2.parkingapp;

/**
* This is a helper class for the strategy design pattern
* It works with the Calculates classes to determine which 
*
*/
public class CalculationType {
    
    private Calculates calculates;
    private double amount;
    
    /**
    * This works with the Calculates classes to determine which calculation to make
    * @return amount - this uses the other calculate methods in the Calculates file to return the amount
    */
    public double getAmount() {
        if (calculates != null) {
            return calculates.calculate();
        }
        return 0;
    }

     /**
    * This sets the type of calculation from the Calculates file whether it's MinMax, LostTicket or SpecialEvent
    * @param  - calculates - an instance of the Calculates class
    * 
    */
    public void setCalculationType(Calculates calculates){
        this.calculates = calculates; 
    }
    
}
