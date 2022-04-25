package com.until.copyImage;
import java.io.File;
import java.util.Optional;

public class FileExtension {

    public FileExtension() {
    }

    /**
     * 
     * @param source   File file = new File("/tmp/test.txt")
     * @return txt
     */
    public static String getFileExtension(File source) {
        //File source = new File("/tmp/test.txt");
        String filename = source.getName();

        String extension = Optional.of(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElse("");
        System.out.println("source :  " + source + " Extension is [" + extension + "]");
        return extension;
    }
}
