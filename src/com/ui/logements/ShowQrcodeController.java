package com.ui.logements;

import com.model.LogementsObj;
import com.generateqrcode.GenerateQRCode;
import com.generateqrcode.ReadQRcode;
import com.generateqrcode.SaveImage;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.global.alerts.Alerts;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class ShowQrcodeController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Button saveButton;

    LogementsObj logementsObj;

    public void setData(LogementsObj logementsObj) {
        this.logementsObj = logementsObj;
        try {
            GenerateQRCode g = new GenerateQRCode();
            g.generateQRCode(logementsObj.toInfo());
            ReadQRcode r = new ReadQRcode();
            r.readQRcode(GenerateQRCode.QRCODE_NAME);
            setImage(GenerateQRCode.QRCODE_NAME);

        } catch (IOException | WriterException | NotFoundException ex) {
            Logger.getLogger(ShowLogementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        saveButton.setOnAction((event) -> {

//            File from = new File(GenerateQRCode.QRCODE_NAME);
//            File toFile = new File("image.png");
//            new SaveImage().Save(from, toFile);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save");
            fileChooser.setInitialFileName("Save file");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.png"));
            File file = fileChooser.showSaveDialog(imageView.getScene().getWindow());

            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG"),
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png")
            );

            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),
                            null), "png", file);
                    new Alerts().successAlert("Image saved in " + file.getName());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    public void setImage(String img) {
        imageView.setImage(new Image(new File(img).toURI().toString()));

    }

}
