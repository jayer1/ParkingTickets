package jayer1.u2.parkingapp;

import java.util.Random;

public class CheckOut {

    private static final int LOW = 1;
    private static final int HIGH = 11;
    private double amount = 0;
    private static final double MIN_FEE = 5;
    private static final double HOURLY_CHARGE = 1;
    private static final double MAX_FEE = 15;
    private static final int TWENTY_FOUR_HOURS = 24;
    private static final int THREE = 3;
    private static final int TWELVE = 12;
    private int duration = 0;
    private static final double LOST_AMOUNT = 25.00;

    public CheckOut() {
        //LOW = this.LOW;
        //HIGH = this.HIGH;
    }

    public int setCheckOutTime() {

        Random hour = new Random();
        int resultHour = hour.nextInt(HIGH - LOW) + LOW;
        return resultHour;
    }

    public double getLostAmount() {
        return LOST_AMOUNT;
    }

    public double setCalcAmount(int elapsedHours) {
        // The fee charged for parked vehicles is based on a $5.00 minimum fee to park for up to three hours. (MIN_FEE)
        // After that there is an additional $1.00 per hour charge for each hour or part of an hour parked. (HOURLY_CHARGE)
        //The maximum charge for any given 24-hour period is $15.00. (MAX_FEE)
        //Assume that no vehicle parks for longer than 24 hours.
        //Lost tickets have a $25.00 fee.

        //amount = 0;
        if (elapsedHours <= THREE) {
            amount = MIN_FEE;
            //System.out.println("Amount is at minimum fee = " + amount);
        } else if (elapsedHours > THREE && elapsedHours <= TWENTY_FOUR_HOURS) {
            amount = MIN_FEE + (elapsedHours - THREE);
            //System.out.println("Elapsed hours = " + elapsedHours);
            //System.out.println("amount = 5 + " + (elapsedHours - THREE));
            //System.out.println("Amount (not min fee) = " + amount);
            if (amount > MAX_FEE) {
                amount = MAX_FEE;
            }
        }
        return amount;
    }

    public int setCalcDuration(int early, int late) {

        //System.out.println("early " + early);
        //System.out.println("late " + late);
        return duration = TWELVE - early + late;

    }

}
