package org.ferris.scriptural.version;

import org.ferris.scriptural.window.version.Version;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class VersionTest {

    @Test
    public void testMe() {
        Version v = new Version();
        Assert.assertEquals("<unknown>", v.getImplementationVersion());
    }
}
