package jayer1.u2.parkingapp;

import java.util.Random;

public class CheckIn {

    public static int checkinTime() {

        Random hour = new Random();
        int low = 7;
        int high = 12;
        int resultHour = hour.nextInt(high - low) + low;
        return resultHour;
    }

}
