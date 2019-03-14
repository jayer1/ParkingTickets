package jayer1.u2.parkingapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {

    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    private String fileName = "ticket.bin";

    public FileStorage() {
        fileName = this.fileName;

    }

    public List<Ticket> FileRead(Ticket ticket) throws ClassNotFoundException {
        fis = null;
        ois = null;
        List<Ticket> myTicketList = null;

        try {
            fis = new FileInputStream(new File(fileName));
            ois = new ObjectInputStream(fis);
            myTicketList = (ArrayList<Ticket>) ois.readObject();
        } catch (IOException ie) {

        } finally {
            try {
                ois.close();
            } catch (IOException io) {

            }
        }

        return myTicketList;
    }

    public void FileWrite(List myTicketList) throws FileNotFoundException, IOException {
        fos = null;
        oos = null;

        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(myTicketList);

        } catch (IOException ie) {

        } finally {
            try {
                oos.close();
            } catch (IOException io) {

            }
        }
    }

}
