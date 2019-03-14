package jayer1.u2.parkingapp;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ParkingAppDriver {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        // TODO code application logic here
        //Scanner keyboard = new Scanner(System.in);
        int vehicleID = 0;
        int earlyTime = 0;
        int lateTime;
        int elapsedHours;
        double amount;
        int amountCheckedOut = 0;
        int lastID = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        Ticket ticket = null;
        int randomPick;
        int randomPickFromArrayList;
        double nbr;

        List<Ticket> myTicketList = new ArrayList();
        // Call FileRead method in FileStorage to read the file into the arrayList
        FileStorage fileReader = new FileStorage();
        myTicketList = fileReader.FileRead(ticket);

        // DATA CLEANUP
        // Set all records to CheckOutHour = 0
        /*for (int i = 0; i < myTicketList.size(); i++) {
         myTicketList.get(i).setCheckOutHour(0);
         myTicketList.get(i).setAmount(0.0);
         }*/
        //Delete records from arraylist and save changes
        //System.out.println("SIZE " + myTicketList.size());
        //myTicketList.remove(myTicketList.size() - 1);
        // arraylist for collecting which slot in arraylist is not checked out
        ArrayList<Integer> checkedOutVehicles = new ArrayList<Integer>();
        amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);
        //System.out.println("amountCheckedOut: " + amountCheckedOut);

        //Display current list
        //displayAllTickets(myTicketList);
        while (true) {

            nbr = (double) Math.random();
            //System.out.println("Number " + nbr);  was in

            if (amountCheckedOut == 0 || nbr > 0.75) { // if there are existing tickets still checked out and random # is <= 0.75
                //if (amountCheckedOut <= 3) { // if there are existing tickets still checked out

                // Display main menu and get input, redisplay menu of input is not 1 or 3
                int mainMenuSelection = 0;

                do {
                    String message = "\n\nBest Value Parking Garage\n"
                            + "\n=========================\n"
                            + "\n1 – Check/In\n\n3 – Close Garage\n\n=> ";
                    mainMenuSelection = getInt(message);

                } while (mainMenuSelection != 1 && mainMenuSelection != 3);

                // 1 = check in, 3 = close garage
                if (mainMenuSelection == 1) { // CHECK IN
                    //System.out.println("list size is " + myTicketList.size()); was in
                    //System.out.println("_+_+_+_+_+_+_+_+_"); was in
                    lastID = myTicketList.get(myTicketList.size() - 1).getVehicleID();
                    //System.out.println("Last id is " + lastID); was in
                    vehicleID = lastID + 1;
                    //vehicleID += 1;

                    // Run the CheckIn class methods
                    CheckIn checkin = new CheckIn();
                    earlyTime = checkin.setCheckinTime();
                    String message = "Vehicle " + vehicleID + " checks in";
                    printMessage(message);
                    ticket = new Ticket(vehicleID, earlyTime, 0, 0.0, false);

                    // Add ticket object to arraylist
                    myTicketList.add(ticket);

                    amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);

                } else if (mainMenuSelection == 3) {  // CLOSE GARAGE
                    summarizeCloseGarage(myTicketList);

                    // Stop the program once you close the display the summary
                    break;
                }

            } else if (nbr <= 0.75) { //else from if amountcheckedout < 3

                // Display main menu and get input, redisplay menu of input is not 1 or 2
                int submitMenuSelection = 1;

                do {

                    String message = "\n\nBest Value Parking Garage\n"
                            + "\n=========================\n"
                            + "\n1 – Check/Out\n\n2 – Lost Ticket\n\n=> ";
                    submitMenuSelection = getInt(message);

                } while (submitMenuSelection != 1 && submitMenuSelection != 2);

                if (submitMenuSelection == 1) { // CHECK OUT

                    randomPickFromArrayList = 0;
                    randomPick = 0;

                    randomPickFromArrayList = getRandomPick(randomPickFromArrayList, amountCheckedOut, randomPick, checkedOutVehicles, myTicketList);

                    /*int number = 0;
                     amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);
                     if (amountCheckedOut == 1) { // If there's only one ticket that's not checked out...
                     number = checkedOutVehicles.get(0);
                     } else { // otherwise get the list of tickets not checked out and pick a random one
                     randomPick = pickRandomCheckOutVehicle(checkedOutVehicles, myTicketList);
                     System.out.println("Random pick: " + randomPick);
                     number = randomPick;
                     }*/
                    // Get the current ticket in the ArrayList and get its checkin time
                    ticket = myTicketList.get(randomPickFromArrayList);
                    earlyTime = ticket.getCheckInHour(); //added this line

                    //Run the CheckOut class methods
                    CheckOut checkout = new CheckOut();
                    lateTime = checkout.setCheckOutTime(); //FROM CheckOut class
                    elapsedHours = checkout.setCalcDuration(earlyTime, lateTime);//FROM CheckOut class
                    amount = checkout.setCalcAmount(elapsedHours); //FROM CheckOut class

                    //Apply results from CheckOut class to current ticket
                    ticket.setCheckOutHour(lateTime);
                    ticket.setAmount(amount);

                    // Display the CheckOut receipt
                    //System.out.println("You checked out at " + lateTime);  was in
                    checkOutDisplay(elapsedHours, randomPickFromArrayList, myTicketList);

                    amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);

                } else if (submitMenuSelection == 2) { // LOST TICKET
                    randomPickFromArrayList = 0;
                    randomPick = 0;

                    randomPickFromArrayList = getRandomPick(randomPickFromArrayList, amountCheckedOut, randomPick, checkedOutVehicles, myTicketList);

                    /*amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);
                     if (amountCheckedOut == 1) { // If there's only one ticket that's not checked out...
                     randomPickFromArrayList = checkedOutVehicles.get(0);
                     } else { // otherwise get the list of tickets not checked out and pick a random one
                     randomPick = pickRandomCheckOutVehicle(checkedOutVehicles, myTicketList);
                     System.out.println("Random pick: " + randomPick);
                     //System.out.println("Which one do you want");
                     randomPickFromArrayList = randomPick;
                     //int number = keyboard.nextInt();
                     }*/
                    ticket = myTicketList.get(randomPickFromArrayList);
                    ticket.setLostTicket(true);

                    // Grab lost ticket final amount from CheckOut class
                    CheckOut lostTicketAmount = new CheckOut();
                    amount = lostTicketAmount.getLostAmount();

                    // Display lost ticket output
                    lostTicketDisplay(df, amount, randomPickFromArrayList, myTicketList);

                    // Update the checkedoutvehicles list
                    amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);
                }
            }
        }
        // Write the arraylist back to the file
        FileStorage fileWriter = new FileStorage();
        fileWriter.FileWrite(myTicketList);

    }

    public static int getRandomPick(int randomPickFromArrayList, int amountCheckedOut, int randomPick, List<Integer> checkedOutVehicles, List<Ticket> myTicketList) {
        amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);
        if (amountCheckedOut == 1) { // If there's only one ticket that's not checked out...
            randomPickFromArrayList = checkedOutVehicles.get(0);
        } else { // otherwise get the list of tickets not checked out and pick a random one
            randomPick = pickRandomCheckOutVehicle(checkedOutVehicles, myTicketList);
            //System.out.println("Random pick: " + randomPick); was in
            //System.out.println("Which one do you want");
            randomPickFromArrayList = randomPick;
            //int number = keyboard.nextInt();
        }
        return randomPickFromArrayList;
    }

    private static int getInt(String message) {
        int response = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.print(message);
        response = keyboard.nextInt();
        keyboard.nextLine();
        return response;
    }

    public static void checkOutDisplay(int elapsedHours, int nbr, List<Ticket> myTicketList) {
        int start = myTicketList.get(nbr).getCheckInHour();
        int end = myTicketList.get(nbr).getCheckOutHour();
        DecimalFormat df = new DecimalFormat("#.00");
        String message = "\n\nBest Value Parking Garage\n\n=========================\n\nReceipt for vehicle ID "
                + myTicketList.get(nbr).getVehicleID() + "\n\n" + elapsedHours + " hours parked "
                + myTicketList.get(nbr).getCheckInHour() + " am - " + myTicketList.get(nbr).getCheckOutHour()
                + " pm" + "\n\n$" + df.format(myTicketList.get(nbr).getAmount());
        printMessage(message);
    }

    public static void lostTicketDisplay(DecimalFormat df, double amount, int nbr, List<Ticket> myTicketList) {
        String message = ("\nBest Value Parking Garage\n\n=========================\nReceipt for vehicle id " + myTicketList.get(nbr).getVehicleID()
                + "\n\nLost Ticket\n\n$" + df.format(amount) + "\n");
        printMessage(message);
    }

    public static int getCheckedOutVehicles(List<Integer> checkedOutVehicles, List<Ticket> myTicketList) {
        // arraylist for collecting which slot in arraylist is not checked out
        checkedOutVehicles.clear();

        // If getCheckOutHour() = 0, add index to checkedOutVehicles arraylist
        for (int i = 0; i < myTicketList.size(); i++) {
            if (myTicketList.get(i).getCheckOutHour() == 0 && myTicketList.get(i).getLostTicket() == false) {
                checkedOutVehicles.add(i);
            }
        }

        // Display the arraylist of Cars
        //System.out.println("Display which slots in the main arraylist have not been checked out");  was in
        //System.out.println("0 = VehicleID 1, etc.");  was in
        for (int i = 0; i < checkedOutVehicles.size(); i++) {
            //System.out.print(checkedOutVehicles.get(i) + " ");  was in
        }
        //System.out.println("");  was in
        int amountNotCheckedOut = checkedOutVehicles.size();
        //System.out.println("Amount of not checked out vehicles " + amountNotCheckedOut);  was in
        return amountNotCheckedOut;
    }

    public static int pickRandomCheckOutVehicle(List<Integer> checkedOutVehicles, List<Ticket> myTicketList) {
        // arraylist for collecting which slot in arraylist is not checked out
        //ArrayList<Integer> checkedOutVehicles = new ArrayList<Integer>();
        checkedOutVehicles.clear();
        for (int i = 0; i < myTicketList.size(); i++) {
            // Lists all tickets pulled into the Arraylist from the file
            //System.out.println(myTicketList.get(i).getVehicleID() + " check in hour " + myTicketList.get(i).getCheckInHour() + " check out hour " + myTicketList.get(i).getCheckOutHour() + " lost ticket? " + myTicketList.get(i).getLostTicket());
            // If getCheckOutHour() = 0, add index to checkedOutVehicles arraylist
            if (myTicketList.get(i).getCheckOutHour() == 0 && myTicketList.get(i).getLostTicket() == false) {
                checkedOutVehicles.add(i);
            }
        }

        // Display the arraylist of open tickets
        //System.out.println("Display which slots in the main arraylist have not been checked out"); was in
        //System.out.println("0 = VehicleID 1, etc."); was in
        for (int i = 0; i < checkedOutVehicles.size(); i++) {
            //System.out.print(checkedOutVehicles.get(i) + " "); was in
        }
        //System.out.println(""); was in

        //System.out.println("Amount of not checked out vehicles " + amountCheckedOut);
        // Randomly grab a slot in the list of checked out vehicles
        Random r = new Random();
        int amountCheckedOut = checkedOutVehicles.size() - 1; // One less than getCheckedOutVehicles
        int slot = r.nextInt((amountCheckedOut - 0) + 1) + 0;
        //System.out.println("So the program picked a random number between 0 and " + amountCheckedOut + " = slot " + slot); was in
        int chosenCheckedOutVehicle = checkedOutVehicles.get(slot);
        return chosenCheckedOutVehicle;
    }

    public static void summarizeCloseGarage(List<Ticket> myTicketList) throws IOException, ClassNotFoundException {
        DecimalFormat df = new DecimalFormat("#.00");
        double amount = 0;
        int lostCount = 0;
        double sumLostCount = 0;
        double total = 0;
        int countCheckIns = 0;

        //Display current list
        //displayAllTickets(myTicketList);
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
        String message = "\nBest Value Parking Garage\n=========================\n\nActivity to Date\n\n$" + df.format(amount) + " was collected from " + countCheckIns + " Check Ins\n\n$" + df.format(sumLostCount) + " was collected from " + lostCount + " Lost Tickets\n\n$" + df.format(total) + " was collected overall\n";

        printMessage(message);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void displayAllTickets(List<Ticket> myTicketList) {
        String message = "";
        for (int i = 0; i < myTicketList.size(); i++) {
            message += "vehicle " + myTicketList.get(i).getVehicleID() + " checkin " + myTicketList.get(i).getCheckInHour()
                    + " am checkout " + myTicketList.get(i).getCheckOutHour() + " amount collected at checkout " + myTicketList.get(i).getAmount() + " Lost Ticket? " + myTicketList.get(i).getLostTicket() + "\n";
        }
        System.out.println("Here's the list of tickets:\n" + message);
    }

}
