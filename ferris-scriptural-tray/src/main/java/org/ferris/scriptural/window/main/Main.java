package org.ferris.scriptural.window.main;

import java.util.Arrays;
import java.util.List;
import javax.enterprise.event.Event;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import org.ferris.scriptural.window.initialization.InitializationEvent;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Main {

    public static void main(String[] args) {
        SeContainer container
            = SeContainerInitializer.newInstance().initialize();

        Main main
            = container.select(Main.class).get();

        main.main(Arrays.asList(args));
    }

    @Inject
    protected Logger log;

    @Inject
    protected Event<InitializationEvent> initializationEvent;

    protected void main(List<String> args) {
        log.info("Fire InitializationEvent");
        initializationEvent.fire(new InitializationEvent());
    }
}
