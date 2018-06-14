package org.ferris.scriptural.window.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.scriptural.window.exit.ExitEvent;
import org.ferris.scriptural.window.initialization.InitializationEvent;
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
    protected UncaughtExceptionView exceptionView;

    @Inject
    protected Event<ExitEvent> exitEvent;

    //protected void init(@Observes @Initialized(ApplicationScoped.class) Object init)
    protected void init(@Observes @Priority(10) InitializationEvent evnt) {
        log.info("Initialize exception handling");
        exceptionTool.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // Remove default handler.  Avoid an infinte uncaught
        // exception circle if another exception occurs.
        exceptionTool.setDefaultUncaughtExceptionHandler(null);

        // Show error
        exceptionView.view(e);

        // Exit
        exitEvent.fire(new ExitEvent());
    }
}
