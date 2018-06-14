package org.ferris.scriptural.window.tray;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.scriptural.window.exit.ExitMenuItem;
import org.ferris.scriptural.window.version.Version;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayView {

    @Inject
    protected Logger log;

    @Inject
    protected Version version;

    @Inject
    protected TrayIcon trayIcon;

    @Inject
    protected TrayPopupMenu popupMenu;

    @Inject
    protected ExitMenuItem exitMenuItem;

    @PostConstruct
    protected void putTheUiTogether() {
        popupMenu.add(exitMenuItem);
        //popupMenu.addSeparator();

        trayIcon.setToolTip(String.format("Ferris Scriptural (%s)", version.getImplementationVersion()));
        trayIcon.setPopupMenu(popupMenu);
        trayIcon.displayMessage("Ferris Scriptural", version.getImplementationVersion(), TrayIcon.MessageType.INFO);
    }

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
