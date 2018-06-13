package org.ferris.scriptural.tray.main;

import java.util.StringJoiner;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class StartupEvent {

    public static final int EXCEPTION = 10;

    public StartupEvent() {}

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "[StartupEvent", "]");
        return sj.toString();
    }
}
