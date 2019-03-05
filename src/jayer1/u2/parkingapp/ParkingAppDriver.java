/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jayer1.u2.parkingapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jayer1.u2.parkingapp.CheckIn.checkinTime;
import static jayer1.u2.parkingapp.CheckOut.checkoutTime;

/**
 *
 * @author ayerj
 */
public class ParkingAppDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        // TODO code application logic here
        Scanner keyboard = new Scanner(System.in);
        //ParkingAppDriver parker = new ParkingAppDriver();
        //String x = "";
        //while (!x.equals("no")) {
        // random hour

        int vehicleID = 0;
        int earlyTime = 0;
        int lateTime;
        int elapsedHours;
        double amount;
        //String keepGoing;
        String end = "n";
        while (end.equals("n")) {

            double nbr = (double) Math.random();
            System.out.println("Number " + nbr);

            if (nbr >= .75) {
                System.out.println("Display check IN prompt");
            } else {
                System.out.println("Display check OUT prompt");
            }
            System.out.println("end now?");
            end = keyboard.nextLine();

        }

        List<Ticket> myTicketList = new ArrayList();
        Ticket ticket;
        //Ticket ticket;
        //keepGoing = "y";
        //while ("y".equals(keepGoing)) {
        while (true) {

            /*mainMenu();

             int mainMenuSelection = keyboard.nextInt();

             if (mainMenuSelection == 1) {
             vehicleID += 1;
             earlyTime = checkinTime();
             System.out.println("You checked in at " + earlyTime);
             ticket = new Ticket(vehicleID, earlyTime, 0, 0.0, false);

             //ticket = new Ticket(vehicleID, earlyTime, 0, 0.0, false);
             myTicketList.add(ticket);

             //System.out.println(" vehicle id " + myTicketList.get(0).getVehicleID() + " checked in " + myTicketList.get(0).getCheckInHour() + " checked out " + myTicketList.get(0).getCheckOutHour() + " amount charged " + myTicketList.get(0).getAmount());
             } else if (mainMenuSelection == 3) {
             summarizeCloseGarage(myTicketList);
             //writeFile(myTicketList);
             break;
             }*/
            double nbr = (double) Math.random();
            System.out.println("Number " + nbr);

            //if (nbr >= .75) {
            System.out.println("Display check IN prompt");

            mainMenu();

            int mainMenuSelection = keyboard.nextInt();

            if (mainMenuSelection == 1) {
                vehicleID += 1;
                earlyTime = checkinTime();
                System.out.println("You checked in at " + earlyTime);
                ticket = new Ticket(vehicleID, earlyTime, 0, 0.0, false);

                //ticket = new Ticket(vehicleID, earlyTime, 0, 0.0, false);
                myTicketList.add(ticket);

                //System.out.println(" vehicle id " + myTicketList.get(0).getVehicleID() + " checked in " + myTicketList.get(0).getCheckInHour() + " checked out " + myTicketList.get(0).getCheckOutHour() + " amount charged " + myTicketList.get(0).getAmount());
            } else if (mainMenuSelection == 3) {
                summarizeCloseGarage(myTicketList);
                //writeFile(myTicketList);
                break;
            }

            //} else {
            System.out.println("Display check OUT prompt");

            submitTicketMenu();

            int submitMenuSelection = keyboard.nextInt();

            if (submitMenuSelection == 1) {
                System.out.println("Which one do you want");
                int number = keyboard.nextInt();
                ticket = myTicketList.get(number);
                lateTime = checkoutTime();
                ticket.setCheckOutHour(lateTime);
                elapsedHours = calcDuration(earlyTime, lateTime);

                //System.out.println("BROKEN OUT \n" + earlyTime + "\n" + lateTime + "\n" + elapsedHours);
                amount = calcAmount(elapsedHours);
                ticket.setAmount(amount);
                checkOutDisplay(number, myTicketList);

            } else if (submitMenuSelection == 2) {
                System.out.println("Which one do you want");
                int number = keyboard.nextInt();
                ticket = myTicketList.get(number);
                ticket.setLostTicket(true);
                amount = 25.00;
                lostTicketDisplay(amount);

            }
            //}

            //System.out.print("Keep going? (y/n): ");
            //keyboard.nextLine();
            //keepGoing = keyboard.nextLine();
            //System.out.println("keepGoing equals " + keepGoing);
        }

        String fileName = "tickets.bin";
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
        Object obj = null;
        while ((obj = is.readObject()) != null) {
            ticket = (Ticket) obj;
            myTicketList.add(ticket);
        }
        System.out.println("first object is " + myTicketList.get(0).getVehicleID());
        is.close();
    }

    public static void mainMenu() {
        System.out.println("\nBest Value Parking Garage");
        System.out.println("\n=========================");
        System.out.println("\n1 – Check/In");
        System.out.println("\n3 – Close Garage");
        System.out.print("\n=> ");
    }

    public static void submitTicketMenu() {
        System.out.println("\nBest Value Parking Garage");
        System.out.println("\n=========================");
        System.out.println("\n1 – Check/Out");
        System.out.println("\n2 – Lost Ticket");
        System.out.print("\n=> ");
    }

    public static void checkOutDisplay(int nbr, List<Ticket> myTicketList) {
        int start = myTicketList.get(nbr).getCheckInHour();
        int end = myTicketList.get(nbr).getCheckOutHour();
        int duration = calcDuration(start, end);
        System.out.println("\nBest Value Parking Garage");
        System.out.println("\n=========================");
        System.out.println("\nReceipt for vehicle ID " + myTicketList.get(nbr).getVehicleID());
        System.out.println("\n" + duration + " hours parked " + myTicketList.get(nbr).getCheckInHour() + " am " + myTicketList.get(nbr).getCheckOutHour() + " pm");
        System.out.print("\n=> ");
    }

    public static double calcAmount(long elapsedHours) {
        // The fee charged for parked vehicles is based on a $5.00
        // minimum fee to park for up to three hours.
        // After that there is an additional $1.00 per hour charge
        // for each hour or part of an hour parked. The maximum charge
        // for any given 24-hour period is $15.00. Assume that no vehicle parks for longer than 24 hours.  Lost tickets have a $25.00 fee.

        double amount = 0;
        if (elapsedHours <= 3) {
            amount = 5.00;
        } else if (elapsedHours > 3 && elapsedHours <= 24) {
            amount = 5 + (elapsedHours - 3);
            if (amount > 15) {
                amount = 15;
            }
        }
        return amount;

    }

    public static int calcDuration(int early, int late) {

        int duration;
        return duration = 12 - early + late;

    }

    public static void lostTicketDisplay(double amount) {
        System.out.println("\nBest Value Parking Garage");
        System.out.println("\n=========================");
        System.out.println("Receipt for vehicle");
        System.out.println("");
        System.out.println("Lost Ticket");
        System.out.print("\n$" + amount);
    }

    public static void summarizeCloseGarage(List<Ticket> myTicketList) throws IOException, ClassNotFoundException {
        double amount = 0;
        int lostCount = 0;
        double sumLostCount = 0;
        double total = 0;
        int countCheckIns = 0;
        for (int i = 0; i < myTicketList.size(); i++) {
            amount += myTicketList.get(i).getAmount();
            if (myTicketList.get(i).getLostTicket() == true) {
                lostCount++;
            }
            if (myTicketList.get(i).getCheckInHour() > 0 && myTicketList.get(i).getCheckOutHour() > 0) {
                countCheckIns++;
            }

        }
        sumLostCount = lostCount * 25;
        total = amount + sumLostCount;
        System.out.println("\nBest Value Parking Garage");
        System.out.println("\n=========================");
        System.out.println("\nActivity to Date");
        System.out.println("\n$" + amount + " was collected from " + countCheckIns + " Check Ins");
        System.out.println("$" + sumLostCount + " was collected from " + lostCount + " Lost Tickets");
        System.out.println("\n$" + total + " was collected overall");

        for (int nbr = 0; nbr < myTicketList.size(); nbr++) {
            System.out.println("vehicle " + myTicketList.get(nbr).getVehicleID() + " checkin " + myTicketList.get(nbr).getCheckInHour() + " am " + myTicketList.get(nbr).getCheckOutHour());
        }
        //System.out.println("Saving to file...");
        //writeFile(myTicketList);

        String fileName = "tickets.bin";
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            // Write the objects
            for (Ticket tics : myTicketList) {
                os.writeObject(tics);
            }

            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeFile(List<Ticket> myTicketList) {
        TicketWriter writer = new TicketWriter();
        writer.writeTicketFile("tickets.txt", myTicketList);
    }

}
