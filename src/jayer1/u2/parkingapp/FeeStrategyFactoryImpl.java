package jayer1.u2.parkingapp;


public class FeeStrategyFactoryImpl implements FeeStrategyFactory{
    
    private int elapsedHours;

    public int getElapsedHours() {
        return elapsedHours;
    }

    public void setElapsedHours(int elapsedHours) {
        this.elapsedHours = elapsedHours;
    }

    @Override
    public FeeStrategy make(String feeName) {
        
        CalculationType calcType = new CalculationType();
        
        switch(feeName){
            
            case "MinxMax":
                return (FeeStrategy) new MinMax(elapsedHours);
                
            case "SpecialEvent":
                return (FeeStrategy) new SpecialEvent();
                
            case "LostTicket":
                return (FeeStrategy) new SpecialEvent();
        }
        return null;
    
    }
}