package jayer1.u2.parkingapp;

import java.io.Serializable;
import java.util.ArrayList;


/**
* This forms the main object for the project a ticket for the parking lot. The properties of the ticket are a vehicle ID, a check in and check out hour, the amount charged for a normal ticket when the car is checked out of the garage
* plus a couple booleans for lost ticket and special event
*/
public class Ticket implements Serializable {

    private int vehicleID;
    private int checkInHour;
    private int checkOutHour;
    private double amount;
    private boolean lostTicket;
    private boolean specialEvent;

    /**
    * This is the ticket constructor
    * @param vehicleID
    * @param checkInHour - number indicating when car was checked into the garage
    * @param checkOutHour - number indicating when car was checked out of the garage
    * @param amount - calculated amount charged for when a normal ticket is checked in and out
    * @param lostTicket - whether or not the ticket was a lost ticket
    * @param specialEvent - whether or not the ticket was a special event
    * @return duration - the duration in time between when the ticket was checked in and when it was checked out
    */
    public Ticket(int vehicleID, int checkInHour, int checkOutHour, double amount, boolean lostTicket, boolean specialEvent) {
        this.vehicleID = vehicleID;
        this.checkInHour = checkInHour;
        this.checkOutHour = checkOutHour;
        this.amount = amount;
        this.lostTicket = lostTicket;
        this.specialEvent = specialEvent;
    }

    public Ticket() {

    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getCheckInHour() {
        return checkInHour;
    }

    public void setCheckInHour(int checkInHour) {
        this.checkInHour = checkInHour;
    }

    public int getCheckOutHour() {
        return checkOutHour;
    }

    public void setCheckOutHour(int checkOutHour) {
        this.checkOutHour = checkOutHour;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean getLostTicket() {
        return lostTicket;
    }

    public void setLostTicket(boolean lostTicket) {
        this.lostTicket = lostTicket;
    }
    
    public boolean getSpecialEvent() {
        return specialEvent;
    }

    public void setSpecialEvent(boolean specialEvent) {
        this.specialEvent = specialEvent;
    }

    public double calcAmount(int elapsedHours) { // SWITCHED FROM LONG ELAPSEDHOURS
        System.out.println("EEEEEHHHHH");
        double amount = 0;
        if (elapsedHours <= 3) {
            amount = 5.00;
        } else if (elapsedHours > 3 && elapsedHours <= 24) {
            amount = 5 + (elapsedHours - 3);
            //System.out.println("Elapsed hours = " + elapsedHours);
            //System.out.println("amount = 5 + " + (elapsedHours - 3));
            //System.out.println("Amount = " + amount);
            if (amount > 15) {
                amount = 15;
            }
        }
        System.out.println("UGH for heaven's sake");
        return amount;

    }
}
