package org.ferris.scriptural.window.tray;

import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.net.URL;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.ferris.scriptural.window.exit.ExitMenuItem;
import org.ferris.scriptural.window.version.Version;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TrayIconProducer {

    @Inject
    protected Version version;

    private TrayIcon trayIcon;

    @Produces
    public TrayIcon produceTrayIcon(Version version, ExitMenuItem exitMenuItem) {
        if (trayIcon == null) {
            // Icon for the taskbar area
            trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().createImage(getUrl()),"");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip(String.format("Ferris Scriptural (%s)", version.getImplementationVersion()));

            // Popup menu
            trayIcon.setPopupMenu(new PopupMenu());

            // Exit menu item
            trayIcon.getPopupMenu().add(exitMenuItem);
        }
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
