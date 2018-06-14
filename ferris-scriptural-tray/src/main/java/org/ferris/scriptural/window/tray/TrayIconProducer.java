package org.ferris.scriptural.window.tray;

import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.net.URL;
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
        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().createImage(getUrl()),"");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(String.format("Ferris Scriptural (%s)", version.getImplementationVersion()));

        return trayIcon;
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
