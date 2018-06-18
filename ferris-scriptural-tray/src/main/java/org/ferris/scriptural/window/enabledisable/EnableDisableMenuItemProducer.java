package org.ferris.scriptural.window.enabledisable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class EnableDisableMenuItemProducer {

    @Inject
    protected Logger log;

    @Inject
    protected Event<EnableEvent> enableEvent;

    @Inject
    protected Event<DisableEvent> disableEvent;


    @Produces
    public EnableDisableMenuItem produceEnableDisableMenuItem() {
        EnableDisableMenuItem mi
            = new EnableDisableMenuItem(enableEvent, disableEvent);
        return mi;
    }
}
