package org.ferris.scriptural.window.exit;

import java.awt.MenuItem;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class ExitMenuItem extends MenuItem {

    private static final long serialVersionUID = 6379912347790948335L;

    protected ExitMenuItem(String label) {
        super(label);
    }
}
