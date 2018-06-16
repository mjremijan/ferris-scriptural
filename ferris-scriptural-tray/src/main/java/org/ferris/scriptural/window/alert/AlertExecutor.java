package org.ferris.scriptural.window.alert;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.scriptural.window.exception.UncaughtExceptionController;
import org.ferris.scriptural.window.initialization.InitializationEvent;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class AlertExecutor extends ScheduledThreadPoolExecutor {

    @Inject
    protected Logger log;

    @Inject
    protected AlertFrequency frequency;

    @Inject
    protected Event<AlertEvent> event;

    @Inject
    protected UncaughtExceptionController exceptionController;

    public AlertExecutor() { super(1); }

    protected void init(@Observes InitializationEvent evnt) {
        log.info("Initialize alert executor");
        alert(new AlertEvent());
    }

    protected void alert(@Observes AlertEvent evnt) {
        this.schedule(
            () -> {
                try {
                    event.fire(evnt);
                } catch (Throwable t) {
                    // So apparently ScheduledThreadPoolExecutor does some nasty
                    // stuff with exceptions.  If an Exception occurs in this
                    // runnable, the pool it will swallow the exception, not
                    // inform you of anything, and stop running everything.
                    // So the way to handle this is to make sure you catch
                    // any exception which may occur and handle them yourself.
                    // The defaultExceptionHandler will **NOT** handle these
                    // exceptions.  This sucks!
                    //
                    // References
                    // - https://stackoverflow.com/questions/6894595/scheduledexecutorservice-exception-handling
                    exceptionController.uncaughtException(Thread.currentThread(), t);
                }
            }
            //, frequency.pick()
            //, TimeUnit.MINUTES
            , 5
            , TimeUnit.SECONDS
        );
    }

    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        log.info(String.format("Schedule to run in %d minutes", delay));
        return super.schedule(command, delay, unit);
    }
}
