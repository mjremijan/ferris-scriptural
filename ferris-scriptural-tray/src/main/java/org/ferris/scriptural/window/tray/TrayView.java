package org.ferris.scriptural.window.tray;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
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
    protected Instance<Alert> alertInstance;

    public void view() {
        SystemTray tray
            = SystemTray.getSystemTray();

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private Alert cache;
    protected void message(@Observes TrayMessageEvent evnt) {

        Platform.runLater(() -> {
            try {
                if (cache != null && cache.isShowing()) {
                    log.info(String.format("Try to close an open alert message %s", cache));
                    cache.close();
                }
                cache = alertInstance.get();
                log.info(String.format("Create new alert message %s", cache));
                cache.setHeaderText(evnt.getCaption());
                cache.setContentText(evnt.getText());

                cache.show();
                cache.setX(
                    Screen.getPrimary().getVisualBounds().getWidth() - cache.getWidth() - 8
                );
                cache.setY(
                    Screen.getPrimary().getVisualBounds().getHeight()- cache.getHeight() - 8
                );
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
