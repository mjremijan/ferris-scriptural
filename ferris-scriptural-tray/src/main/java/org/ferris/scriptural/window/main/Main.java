package org.ferris.scriptural.window.main;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.ferris.scriptural.window.initialization.InitializationEvent;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class Main implements Runnable {

    @Inject
    protected Logger log;

    @Inject
    protected Event<InitializationEvent> initializationEvent;

    @Override
    public void run() {
        log.info("Fire InitializationEvent");
        initializationEvent.fire(new InitializationEvent());
    }
}
