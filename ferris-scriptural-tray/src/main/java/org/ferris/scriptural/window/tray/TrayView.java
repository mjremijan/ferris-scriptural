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
        Platform.runLater(() -> {
            try {
                if (alert.isShowing()) {
                    log.info(String.format("Try to close an open alert message %s", alert));
                    alert.close();
                }

                log.info(String.format("Create new alert message %s", alert));
                alert.setHeaderText(evnt.getCaption());
                alert.setContentText(evnt.getText());

                alert.show();
                alert.setX(
                    Screen.getPrimary().getVisualBounds().getWidth() - alert.getWidth() - 8
                );
                alert.setY(
                    Screen.getPrimary().getVisualBounds().getHeight()- alert.getHeight() - 8
                );
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
