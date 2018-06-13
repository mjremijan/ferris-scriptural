package org.ferris.scriptural.window.tray;

import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.net.URL;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayIconProducer {

    @Produces
    public TrayIcon produceTrayIcon() {
        return new TrayIcon(Toolkit.getDefaultToolkit().createImage(getUrl()));
    }

    protected URL getUrl() {
        String path
            = "/tray.png";

        URL resource
            = getClass().getResource(path);

        if (resource == null) {
            throw new RuntimeException(String.format("The resource \"%s\" was not found on the class path", path));
        }

        return resource;
    }
}
