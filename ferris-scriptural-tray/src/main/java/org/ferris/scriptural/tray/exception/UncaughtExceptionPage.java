package org.ferris.scriptural.tray.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class UncaughtExceptionPage {

    public void show(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        JOptionPane.showMessageDialog(null, sw.toString(), "Uncaught Error", JOptionPane.ERROR_MESSAGE);
    }
}
