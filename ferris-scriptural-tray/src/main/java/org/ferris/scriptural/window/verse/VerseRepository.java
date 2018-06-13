package org.ferris.scriptural.window.verse;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.ferris.scriptural.window.conf.Conf;
import org.ferris.scriptural.window.net.URLConnectionTool;
import org.ferris.scriptural.window.retry.ExceptionRetry;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class VerseRepository {

    @Inject
    protected Logger log;

    @Inject @Conf("scriptural.verses.url")
    protected URL versesUrl;

    @Inject
    protected URLConnectionTool uct;

    @ExceptionRetry
    List<Verse> findAll() {
        log.info("ENTER");

        List<Verse> verses = new LinkedList();

        try (
            LineNumberReader reader
                = new LineNumberReader(new InputStreamReader(uct.getConnection(versesUrl).getInputStream()));
        ) {
            String title = null;
            String location = null;
            String text = null;

            for (String line=reader.readLine(); line != null; line=reader.readLine()) {
                line = StringUtils.trimToNull(line);

                // skip if line is empty
                if (line == null) {
                    continue;
                }

                // skip if line is comment
                if (line.startsWith("//")) {
                    continue;
                }

                // title
                if (line.startsWith("o")) {
                    title = line.substring(1).trim();
                    if (title.isEmpty()) {
                        throw new RuntimeException(
                            String.format("Empty title on line %d", reader.getLineNumber())
                        );
                    }
                    location = null;
                    continue;
                }

                // location
                if (location == null) {
                    location = line;
                    text = null;
                    continue;
                }

                // text
                if (text == null) {
                    text = line;
                }

                // add?
                if (title != null && location != null && text != null) {
                    verses.add(new Verse(title, location, text));
                    location = null;
                    text = null;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception finding all verses", e);
        }

        return verses;
    }
}
