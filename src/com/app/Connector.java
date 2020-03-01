package com.app;

import java.io.*;
import java.util.ArrayList;

public class Connector {

    private File file;

    public Connector(File file) {
        this.file = file;
    }

    public void write(ArrayList<ColorLine> arr) throws IOException {
        FileOutputStream fos = new FileOutputStream (file);
        try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
            oos.writeObject(arr);
            oos.flush();
        }
    }

    public ArrayList<ColorLine> read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
            return (ArrayList<ColorLine>)oin.readObject();
        }
    }
}
