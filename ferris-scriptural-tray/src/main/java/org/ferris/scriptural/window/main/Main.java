package org.ferris.scriptural.window.main;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.event.Event;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import org.ferris.scriptural.window.initialization.InitializationEvent;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Main {

    public static void main(String[] args) {
        SeContainer container
            = SeContainerInitializer.newInstance().initialize();

        Main main
            = container.select(Main.class).get();

        main.main(Arrays.asList(args));
    }

    @Inject
    protected Logger log;

    @Inject
    protected Event<InitializationEvent> initializationEvent;

    protected void main(List<String> args) {
        run();

        log.info("Fire InitializationEvent");
        initializationEvent.fire(new InitializationEvent());
    }

    public void run() {
        try {
            Image image =
                Toolkit.getDefaultToolkit().createImage(getClass().getResource("/purple.png"));

            TrayIcon trayIcon
                = new TrayIcon(image, "");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("System tray icon demo");


            SystemTray tray = SystemTray.getSystemTray();
            tray.add(trayIcon);

            System.out.printf("Display message%n");
            trayIcon.displayMessage("Be a servant", "Book c:a-b", TrayIcon.MessageType.INFO);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
