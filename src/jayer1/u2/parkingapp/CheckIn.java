package jayer1.u2.parkingapp;

import java.util.Random;

public class CheckIn {

    private static final int LOW = 7;
    private static final int HIGH = 12;

    public CheckIn() {
        //LOW = this.LOW;
        //HIGH = this.HIGH;
    }

    public int setCheckinTime() {

        Random hour = new Random();
        int resultHour = hour.nextInt(HIGH - LOW) + LOW;
        return resultHour;
    }

    /* old way
     public static int checkinTime(int low, int high) {

     Random hour = new Random();
     int low = 7;
     int high = 12;
     int resultHour = hour.nextInt(high - low) + low;
     return resultHour;
     }
     */
}
