package com.until.copyImage;

import java.io.File;

public class RenameFile {

    public RenameFile() {
    }
    public boolean rename;

    /**
     * @return true if file is renamed
     */
    public boolean isRename() {
        return rename;
    }

    /**
     * @param rename rename file from name to anther name
     */
    public void setRename(boolean rename) {
        this.rename = rename;
    }

    /**
     * @param from File file = new File("image.jpg");
     * @param toName File file = new File("produce1.jpg");
     * @return produce1.jpg
     */
    public File renameTo(String from, String toName) {
        // for check is rename
        setRename(false);
        File fromFile = new File(from);
        File toFile = new File(toName);
        if (fromFile.renameTo(toFile)) {
            System.out.println("File Successfully Renamed from [" + from + "] to [" + toName + "]");
            setRename(true);
        } else {
            System.out.println("Operation Failed");
        }
        return toFile;
    }

}
