package com.generateqrcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImage {

    public void Save(File from, File toFile) {

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(from);
            fos = new FileOutputStream(toFile);
            int c;
            while ((c = fis.read()) != -1) {
                fos.write(c);
            }
            System.out.println("Saved  successfully");
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());

        } finally {

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
            }
        }
    }

}
