package org.ferris.scriptural.window.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.scriptural.window.exit.ExitEvent;
import org.ferris.scriptural.window.main.StartupEvent;
import static org.ferris.scriptural.window.main.StartupEvent.EXCEPTION;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class UncaughtExceptionService implements UncaughtExceptionHandler {

    @Inject
    protected Logger log;

    @Inject
    protected UncaughtExceptionTool exceptionTool;

    @Inject
    protected UncaughtExceptionPage exceptionPage;

    @Inject
    protected Event<ExitEvent> exitEvent;

    public void observesStartup(
            @Observes @Priority(EXCEPTION) StartupEvent event
    ) {
        log.info(String.format("ENTER %s", event));

        log.debug("Set default uncaught exception handler");
        exceptionTool.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        // Remove default handler...infinte circle?
        exceptionTool.setDefaultUncaughtExceptionHandler(null);

        // Show error
        exceptionPage.show(e);

        // Exit
        exitEvent.fire(new ExitEvent());
    }
}
