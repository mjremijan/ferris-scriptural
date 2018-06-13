package org.ferris.scriptural.window.conf;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConfProducer {
    private Properties props;

    @Inject
    public ConfProducer(ConfDirectory confDirectory) throws Exception {
        props = new Properties();
        props.load(new FileInputStream(new File(confDirectory,"conf.properties")));
    }

    @Produces @Conf
    public String produceStringProperty(InjectionPoint ip) {
        Conf m = ip.getAnnotated().getAnnotation(Conf.class);
        return props.getProperty(m.value(), "-UNKNOWN-");
    }

    @Produces @Conf
    public URL produceURLProperty(InjectionPoint ip) {
        try {
            return new URL(produceStringProperty(ip));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
