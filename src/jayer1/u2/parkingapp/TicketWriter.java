/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jayer1.u2.parkingapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author ayerj
 */
public class TicketWriter {

    public void writeTicketFile(String fileName, List<Ticket> myTicketList) {
        ObjectOutputStream oos = null;
        System.out.println("fileName = " + fileName);
        System.out.println("array size = " + myTicketList.size());

        try {
            // Create the streams
            FileOutputStream fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);

            // Write the objects
            for (Ticket tics : myTicketList) {
                oos.writeObject(tics);
            }

            System.out.println("Tickets successfully written");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the file
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
