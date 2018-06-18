package org.ferris.scriptural.window.enabledisable;

import java.awt.MenuItem;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class EnableDisableMenuItem extends MenuItem {

    private static final long serialVersionUID = 6379912347790948335L;

    private boolean enabled;
    protected Event<EnableEvent> enableEvent;
    protected Event<DisableEvent> disableEvent;

    protected EnableDisableMenuItem(Event<EnableEvent> enableEvent, Event<DisableEvent> disableEvent) {
        super();
        this.enableEvent = enableEvent;
        this.disableEvent = disableEvent;
        this.enabled = true;
        super.setLabel("Click to Disable");
        super.addActionListener(l -> {
            if (enabled) {
                enabled = false;
                super.setLabel("Click to Enable");
                disableEvent.fire(new DisableEvent());
            } else {
                enabled = true;
                super.setLabel("Click to Disable");
                enableEvent.fire(new EnableEvent());
            }
        });
    }
}
