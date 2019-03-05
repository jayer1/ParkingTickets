/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jayer1.u2.parkingapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ayerj
 */
public class Ticket implements Serializable {

    private int vehicleID;
    private int checkInHour;
    private int checkOutHour;
    private double amount;
    private boolean lostTicket;

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

    public Ticket(int vehicleID, int checkInHour, int checkOutHour, double amount, boolean lostTicket) {
        this.vehicleID = vehicleID;
        this.checkInHour = checkInHour;
        this.checkOutHour = checkOutHour;
        this.amount = amount;
        this.lostTicket = lostTicket;
    }

    public Ticket() {

    }
}
