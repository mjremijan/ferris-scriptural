package org.ferris.scriptural.window.exit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class ExitMenuItemProducer {

    @Inject
    protected Event<ExitEvent> exitEvent;

    @Produces
    public ExitMenuItem produceExitMenuItem() {
        ExitMenuItem mi
            = new ExitMenuItem("Exit");
        mi.addActionListener(e -> exitEvent.fire(new ExitEvent()));
        return mi;
    }
}
