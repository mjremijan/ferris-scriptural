package org.ferris.scriptural.tray.exit;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class ExitObserver {

    @Inject
    protected ExitTool systemTool;

    public void observes(@Observes ExitEvent exitEvent) {
        systemTool.exitAbnormal();
    }

}
