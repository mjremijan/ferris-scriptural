package org.ferris.scriptural.window.tray;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.ferris.scriptural.window.exit.ExitMenuItem;
import org.ferris.scriptural.window.util.version.Version;
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
    public void putTheUiTogether() {
        log.info("ENTER");

        popupMenu.add(exitMenuItem);
        //popupMenu.addSeparator();

        trayIcon.setToolTip(String.format("Ferris Scriptural (%s)", version.getImplementationVersion()));
        trayIcon.setPopupMenu(popupMenu);
    }

    public void show() {
        log.info("ENTER");

        SystemTray tray
            = SystemTray.getSystemTray();

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
