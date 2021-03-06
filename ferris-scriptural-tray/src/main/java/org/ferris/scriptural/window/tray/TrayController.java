package org.ferris.scriptural.window.tray;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.scriptural.window.alert.AlertEvent;
import org.ferris.scriptural.window.enabledisable.DisableEvent;
import org.ferris.scriptural.window.enabledisable.EnableEvent;
import org.ferris.scriptural.window.initialization.InitializationEvent;
import org.ferris.scriptural.window.verse.VerseServices;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TrayController {
    @Inject
    protected Logger log;

    @Inject
    protected TrayView trayView;

    @Inject
    protected Event<TrayMessageEvent> trayMessageEvent;

    private boolean enabled;

    protected void init(@Observes InitializationEvent evnt) {
        log.info("Initialize tray view");
        enabled = true;
        trayView.view();
    }

    protected void alert(@Observes AlertEvent evnt, VerseServices verseServices) {
        log.info("Got the AlertEvent");
        if (enabled) {
            log.info("Enabled, so proceeding.");
            verseServices.pick().ifPresent(v -> {
                String caption = v.getTitle();
                String text = v.getText() + " (" + v.getLocation() + ")";
                trayMessageEvent.fire(new TrayMessageEvent(caption, text));
            });
        } else {
            log.info("Disabled, so stopping.");
        }
    }

    protected void enable(@Observes EnableEvent evnt) {
        log.info("Got the EnableEvent");
        enabled = true;
    }

    protected void disable(@Observes DisableEvent evnt) {
        log.info("Got the DisableEvent");
        enabled = false;
    }
}
