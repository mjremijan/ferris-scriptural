package org.ferris.scriptural.window.conf;

import java.io.File;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class ConfDirectory extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    ConfDirectory(File parentFile) {
        super(parentFile, "conf");
    }

}
