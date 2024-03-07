package com.umiomikket.crearengine.utils;

import java.io.*;

public class ObjectFile {
    public FileOutputStream fileOutput;

    public ObjectFile(String fileName, Object objectToWrite) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(objectToWrite);
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }

        fileOutput = fos;
    }
}
