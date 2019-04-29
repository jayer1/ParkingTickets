package jayer1.u2.parkingapp;

public class CalculationType {
    
    private Calculates calculates;
    private double amount = 0;
    
    public double getAmount() {
        return amount;
    }
    
    public void setCalculationType(Calculates calculates){
        this.calculates = calculates; 
    }
    
    /*public void calculate(){
        if(calculates !=null){
            amount = calculates.calculate();
        }
    }*/
    
}
