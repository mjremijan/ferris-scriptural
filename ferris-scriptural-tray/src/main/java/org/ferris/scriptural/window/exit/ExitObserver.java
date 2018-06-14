package org.ferris.scriptural.window.exit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class ExitObserver {

    @Inject
    protected ExitTool systemTool;

    public void observes(@Observes ExitEvent exitEvent) {
        systemTool.exitAbnormal();
    }

}
