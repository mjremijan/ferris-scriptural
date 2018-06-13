package org.ferris.scriptural.window.verse;

import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class VerseServices {

    @Inject
    protected Logger log;

    @Inject
    protected VerseRepository repository;

    public void logAll() {
        log.info("ENTER");
        repository.findAll().forEach(v -> log.info(v.toString()));
    }
}
