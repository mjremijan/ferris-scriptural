package org.ferris.scriptural.raw;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.net.URLConnection;
import org.junit.Test;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class RawTest {


    @Test
    public void rawTest() throws Exception {
        URL url
            = new URL("https://raw.githubusercontent.com/mjremijan/ferris-scriptural/master/ferris-scriptural-verses/src/main/verses/verses.txt");

        URLConnection conn
            = url.openConnection();

        InputStream is
            = conn.getInputStream();

        LineNumberReader reader
            = new LineNumberReader(new InputStreamReader(is));

        for (String line=reader.readLine(); line != null; line=reader.readLine()) {
            System.out.printf("%s%n", line);
        }

    }
}
