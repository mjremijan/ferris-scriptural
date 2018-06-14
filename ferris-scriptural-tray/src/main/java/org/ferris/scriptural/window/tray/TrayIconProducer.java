package org.ferris.scriptural.window.tray;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.ferris.scriptural.window.version.Version;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayIconProducer {

    @Inject
    protected Version version;

    @Produces
    public TrayIcon produceTrayIcon() {

        Image image =
                Toolkit.getDefaultToolkit().createImage(getClass().getResource("/purple.png"));

            TrayIcon trayIcon
                = new TrayIcon(image, "");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("System tray icon demo");

        System.out.printf("Produce TrayIcon %s%n", trayIcon);
            return trayIcon;
    }

//    @Produces
//    public TrayIcon produceTrayIcon() {
//        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().createImage(getUrl()),"");
//        trayIcon.setImageAutoSize(true);
//        trayIcon.setToolTip(String.format("Ferris Scriptural (%s)", version.getImplementationVersion()));
//
//        return trayIcon;
//    }
//
//    protected URL getUrl() {
//        String path
//            = "/tray.png";
//
//        URL resource
//            = getClass().getResource(path);
//
//        if (resource == null) {
//            throw new RuntimeException(String.format("The resource \"%s\" was not found on the class path", path));
//        }
//
//        return resource;
//    }
}
