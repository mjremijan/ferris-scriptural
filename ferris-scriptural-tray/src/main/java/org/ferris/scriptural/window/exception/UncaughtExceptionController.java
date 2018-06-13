package org.ferris.scriptural.window.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.scriptural.window.exit.ExitEvent;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class UncaughtExceptionController implements UncaughtExceptionHandler {

    @Inject
    protected Logger log;

    @Inject
    protected UncaughtExceptionTool exceptionTool;

    @Inject
    protected UncaughtExceptionView exceptionPage;

    @Inject
    protected Event<ExitEvent> exitEvent;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        log.info("Set default uncaught exception handler");
        exceptionTool.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // Remove default handler.  Avoid an infinte uncaught
        // exception circle if another exception occurs.
        exceptionTool.setDefaultUncaughtExceptionHandler(null);

        // Show error
        exceptionPage.view(e);

        // Exit
        exitEvent.fire(new ExitEvent());
    }
}
