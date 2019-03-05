package jayer1.u2.parkingapp;

import java.util.Random;

public class CheckOut {

    public static int checkoutTime() {

        Random hour = new Random();
        int low = 7;
        int high = 12;
        int resultLateHour = hour.nextInt(high - low) + low;
        return resultLateHour;

    }

}
