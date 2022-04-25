package com.global.alerts;

import com.module.media.img.NotificationIcons;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class SFXNotifications {

    public void success(String title, String message) {
        dialog(title, message, NotificationIcons.SUCCESS);
    }

    public void info(String title, String message) {
        dialog(title, message, NotificationIcons.INGO);
    }

    public void warning(String title, String message) {
        dialog(title, message, NotificationIcons.WARNING);
    }

    public void error(String title, String message) {
        dialog(title, message, NotificationIcons.ERROR);
    }
    // this is for show notification 
    private void dialog(String title, String message, String type) {
        // this is image for notifi
        Image img = new Image(type);
        Notifications notf = Notifications.create()
                .title(title)
                .text(message)
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction((ev) -> {
                    System.out.println("on click ");
                });
        // dark mode if you went 
        notf.darkStyle();
        // show 
        notf.show();
    }
}
