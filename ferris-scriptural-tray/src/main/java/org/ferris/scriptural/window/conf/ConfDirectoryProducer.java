package org.ferris.scriptural.window.conf;

import java.net.URISyntaxException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.ferris.scriptural.window.application.*;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class ConfDirectoryProducer {

    private ConfDirectory singleton;

    @Produces
    public ConfDirectory getConfDirectory(ApplicationDirectory appDir) throws URISyntaxException {
        if (singleton == null) {
            singleton = new ConfDirectory(appDir);
        }
        return singleton;
    }
}
