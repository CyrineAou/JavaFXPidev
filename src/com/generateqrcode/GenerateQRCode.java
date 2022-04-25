package com.generateqrcode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class GenerateQRCode {

    public String directory_path = "data/qrcode/img/";
    public static String QRCODE_NAME = "data/qrcode/img/qrcode.png";

    private void generate(String data, String path, String charset,
            Map map, int h, int w) throws WriterException, IOException {
        BitMatrix matrix
                = new MultiFormatWriter().encode(
                        new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter
                .writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1),
                        new File(path));
    }

    public void generateQRCode(String information) throws IOException, WriterException {
        File directory = new File(directory_path);

        if (!directory.exists()) {
            System.out.println("directory not exists [" + directory.getName() + "]");
            directory.mkdirs();
            System.out.println("directory created [" + directory.getName() + "]");
        }
        String path = directory_path + "qrcode.png";
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        generate(information, path, charset, hashMap, 200, 200);
        System.out.println("QR Code created successfully. [" + path + "]");

    }
}
