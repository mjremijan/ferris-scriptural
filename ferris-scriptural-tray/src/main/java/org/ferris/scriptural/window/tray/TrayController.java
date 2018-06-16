package org.ferris.scriptural.window.tray;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.scriptural.window.alert.AlertEvent;
import org.ferris.scriptural.window.initialization.InitializationEvent;
import org.ferris.scriptural.window.verse.Verse;
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

    protected void init(@Observes InitializationEvent evnt) {
        log.info("Initialize tray view");
        trayView.view();
    }

    protected void alert(@Observes AlertEvent evnt, VerseServices verseServices) {
        log.info("Got the AlertEvent");
        Verse v = verseServices.pick();
        String caption = v.getTitle();
        String text = v.getText() + " (" + v.getLocation() + ")";
        trayMessageEvent.fire(new TrayMessageEvent(caption, text));
    }
}
