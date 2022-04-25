package com.until.copyImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Java Program to Copy file using File Stream
// Importing input output classes
public class CopyImage {

    public String directory_path = "data/produce/img/";
    String fromName;
    String toName;

    public void copy(File from, String toAntherLocation) {
        // Creating two stream
        // one input and other output
        FileInputStream fis = null;
        FileOutputStream fos = null;

        // Try block to check for exceptions
        try {
            // Initializing both the streams with
            // respective file directory on local machine
            // Custom directory path on local machine
            fis = new FileInputStream(from);
            File directory = new File(directory_path);
            if (!directory.exists()) {
                System.out.println("directory created ....");
                directory.mkdirs();
            }
            // Custom directory path on local machine
            File file = new File(directory.getAbsoluteFile() + "/" + from.getName());
            if (file.exists()) {
                System.out.println("file is exists ");
                return;
            }
            toName = directory_path + from.getName();
            if (new File(toName).exists()) {
                System.out.println("Rename process is failed File is aurade exists ");
                return;
            }
            fos = new FileOutputStream(file);
            int c;
            // Condition check
            // Reading the input file till there is input
            // present
            while ((c = fis.read()) != -1) {
                // Writing to output file of the specified
                // directory
                fos.write(c);
            }
            // By now writing to the file has ended, so
            // Display message on the console
            System.out.println("Copied the file successfully");
            // for rename file to new name 
            fromName = directory_path + from.getName();

        } catch (IOException ex) {
            // IOException
            System.out.println(ex.getLocalizedMessage());
            Logger.getLogger(CopyImage.class.getName()).log(Level.SEVERE, null, ex);

        }// Optional finally keyword but is good practice to
        // empty the occupied space is recommended whenever
        // closing files,connections,streams
        finally {
            // Closing the streams
            if (fis != null) {
                try {
                    // Closing the fileInputStream
                    fis.close();
                } catch (IOException ex) {
                    System.out.println(ex.getLocalizedMessage());
                    Logger.getLogger(CopyImage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (fos != null) {
                try {
                    // Closing the fileOutputStream
                    fos.close();
                } catch (IOException ex) {
                    System.out.println(ex.getLocalizedMessage());
                    Logger.getLogger(CopyImage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //---------------------------
//        System.out.println("From :" + fromName + " >> " + toName);
//        if (!new File(toName).exists()) {
//            new RenameFile().renameTo(fromName, toName);
//        } else {
//            System.out.println("Rename process is failed File is aurade exists ");
//        }
    }

    public String imageCode() {
        return toName;
    }
}
