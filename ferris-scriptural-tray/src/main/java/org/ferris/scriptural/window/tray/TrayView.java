package org.ferris.scriptural.window.tray;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.scriptural.window.exit.ExitMenuItem;
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
    protected TrayPopupMenu popupMenu;

    @Inject
    protected ExitMenuItem exitMenuItem;

    @PostConstruct
    protected void putTheUiTogether() {
//        popupMenu.add(exitMenuItem);
//        //popupMenu.addSeparator();
//
//
//        trayIcon.setPopupMenu(popupMenu);

    }

//    public void run() {
//        System.out.printf("RUNNING TEST TRAY ICON 2%n");
//        try {
//            Image image =
//                Toolkit.getDefaultToolkit().createImage(getClass().getResource("/purple.png"));
//
//            TrayIcon trayIcon
//                = new TrayIcon(image, "");
//            trayIcon.setImageAutoSize(true);
//            trayIcon.setToolTip("System tray icon demo");
//
//
//            SystemTray tray = SystemTray.getSystemTray();
//            tray.add(trayIcon);
//
//            System.out.printf("Display message%n");
//            trayIcon.displayMessage("Be a servant", "Book c:a-b", TrayIcon.MessageType.INFO);
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }

    public void view() {
        SystemTray tray
            = SystemTray.getSystemTray();

        try {
            System.out.printf("VIEW: this=%s, trayIcon=%s%n", this, trayIcon);
            tray.add(trayIcon);
            trayIcon.displayMessage("Ferris Scriptural", trayIcon.getToolTip(), TrayIcon.MessageType.INFO);

        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    protected void message(@Observes TrayMessageEvent evnt) {
        log.info(String.format("Got the TrayMessageEvent %s", evnt));
        System.out.printf("MESSAGE: this=%s, trayIcon=%s%n", this, trayIcon);
        trayIcon.displayMessage(evnt.getCaption(), evnt.getText(), evnt.getMessageType());
    }
}
