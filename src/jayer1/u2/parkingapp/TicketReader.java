/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jayer1.u2.parkingapp;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ayerj
 */
public class TicketReader {

    public List<Ticket> readTicketFile(String fileName) {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        ObjectInputStream ois = null;

        try {
            FileInputStream fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);

            Object obj = null;
            // Allow the EOF Exception to happen
            try {
                while ((obj = ois.readObject()) != null) {
                    Ticket ticket = (Ticket) obj;
                    ticketList.add(ticket);
                }
            } catch (EOFException e) {
                // Do nothing, we expect this to happen
            }

            System.out.println("Tickets successfully read");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ticketList;
    }

}
