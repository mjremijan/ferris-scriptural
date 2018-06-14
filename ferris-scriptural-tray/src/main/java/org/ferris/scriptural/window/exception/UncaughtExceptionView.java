package org.ferris.scriptural.window.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.inject.Inject;
import javax.swing.JOptionPane;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class UncaughtExceptionView {

    @Inject
    protected Logger log;

    public void view(Throwable t) {
        log.error("View Error", t);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        pw.flush();
        JOptionPane.showMessageDialog(null, sw.toString(), "Scriptural Uncaught Error", JOptionPane.ERROR_MESSAGE);
    }
}
