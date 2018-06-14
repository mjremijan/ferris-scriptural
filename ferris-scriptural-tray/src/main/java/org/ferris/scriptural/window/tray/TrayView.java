package org.ferris.scriptural.window.tray;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
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
        trayIcon.displayMessage(evnt.getCaption(), evnt.getText(), evnt.getMessageType());
    }
}
