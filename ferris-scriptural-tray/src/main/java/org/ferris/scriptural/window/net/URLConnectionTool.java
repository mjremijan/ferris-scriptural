package org.ferris.scriptural.window.net;

import java.net.URL;
import java.net.URLConnection;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class URLConnectionTool {

    public URLConnection getConnection(URL url) {
        URLConnection conn = null;
        try {
           conn = url.openConnection();
        } catch (Exception e) {
            throw new RuntimeException();
        }

        if (conn == null) {
            throw new RuntimeException(
                String.format("URLConnection is null for URL \"%s\"", String.valueOf(url))
            );
        }
        return conn;
    }
}
