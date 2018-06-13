package org.ferris.scriptural.window.alert;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class AlertExecutor {

    @Inject
    protected Logger log;

    @Inject
    protected AlertFrequency frequency;

    protected ScheduledExecutorService executor;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        log.info("Start alert executor");

        executor = Executors.newScheduledThreadPool(1);

//         Runnable messageProcessingTask = () -> {
//                try {
//                    List<Message> messages = serviceA.receiveMessages();
//                    messages.forEach(m -> {
//                        boolean success = serviceB.doSomething(m);
//                        if (success) serviceB.deleteMessage(m);
//                        else LOG.error("failed to process the message bla bla...");
//                    });
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            };


        int runIn = frequency.getRandom();
        log.info(String.format("Run in %d minutes", runIn));
        executor.schedule(
            () -> log.info("I'm just ran!%n")
            , runIn
            , TimeUnit.MINUTES);
    }
}
