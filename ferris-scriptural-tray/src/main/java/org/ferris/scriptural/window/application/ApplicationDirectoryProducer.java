package org.ferris.scriptural.window.application;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class ApplicationDirectoryProducer {

    private ApplicationDirectory singleton;

    @Produces
    public ApplicationDirectory getApplicationDirectory() throws URISyntaxException {
        if (singleton == null) {
            // This code assumes the following directory structure
            //
            // /app
            //    /bin
            //    /lib
            //      ferris-app-1.0.0.0.jar
            //    /logs
            //
            // So the the application directory will be 1
            // directory up from where the JAR file is located.
            URL jarURL = ApplicationDirectory.class.getProtectionDomain().getCodeSource().getLocation();
            URI jarURI = jarURL.toURI();
            File jarFile = new File(jarURI);
            File appFile = jarFile.getParentFile().getParentFile();
            singleton = new ApplicationDirectory(appFile.getAbsolutePath());
        }
        return singleton;
    }
}
