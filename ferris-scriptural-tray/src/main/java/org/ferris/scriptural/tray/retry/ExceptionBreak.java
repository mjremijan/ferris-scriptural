package org.ferris.scriptural.tray.retry;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ExceptionBreak extends RuntimeException {

    private static final long serialVersionUID = 17609257745989856L;

    public ExceptionBreak(String msg) {
        super(msg);
    }
}
