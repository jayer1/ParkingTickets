package jayer1.u2.parkingapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable {

    private int vehicleID;
    private int checkInHour;
    private int checkOutHour;
    private double amount;
    private boolean lostTicket;

    public Ticket(int vehicleID, int checkInHour, int checkOutHour, double amount, boolean lostTicket) {
        this.vehicleID = vehicleID;
        this.checkInHour = checkInHour;
        this.checkOutHour = checkOutHour;
        this.amount = amount;
        this.lostTicket = lostTicket;
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

    public double calcAmount(int elapsedHours) { // SWITCHED FROM LONG ELAPSEDHOURS
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
        return amount;

    }
}
