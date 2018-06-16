package org.ferris.scriptural.window.tray;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TrayView {

    @Inject
    protected Logger log;

    @Inject
    protected TrayIcon trayIcon;

    @Inject
    protected Alert alert;

    public void view() {
        SystemTray tray
            = SystemTray.getSystemTray();

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }


    protected void message(@Observes TrayMessageEvent evnt) {
        log.info(String.format("Got the TrayMessageEvent %s", evnt));
        //trayIcon.displayMessage(evnt.getCaption(), evnt.getText(), evnt.getMessageType());
        if (alert.isShowing()) {
            log.info("Try to close an open alert message");
            Platform.runLater(() -> { alert.close(); });
        }
        while (alert.isShowing()) {
            try {
                log.info("Sleep while alert message is still showing");
                Thread.sleep(500);
            } catch (InterruptedException ignore) {}
        }

        Platform.runLater(() -> {
            log.info("Show new alert message");
            alert.setHeaderText(evnt.getCaption());
            alert.setContentText(evnt.getText());

            alert.show();
            alert.setX(
                Screen.getPrimary().getVisualBounds().getWidth() - alert.getWidth() - 8
            );
            alert.setY(
                Screen.getPrimary().getVisualBounds().getHeight()- alert.getHeight() - 8
            );
        });
    }
}
