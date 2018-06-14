package org.ferris.scriptural.window.conf;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import org.ferris.scriptural.window.conf.qualifier.Conf;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class ConfPropertyProducer {

    private Properties props;

    @Inject
    public ConfPropertyProducer(ConfDirectory confDirectory) {
        props = new Properties();
        try {
            props.load(new FileInputStream(new File(confDirectory, "conf.properties")));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @Produces
    @Conf
    public String produceStringProperty(InjectionPoint ip) {
        Conf m = ip.getAnnotated().getAnnotation(Conf.class);
        if (props == null) {
            throw new RuntimeException("WHY IS PROPS NULL?");
        }
        return props.getProperty(m.value(), "-UNKNOWN-").trim();
    }

    @Produces
    @Conf
    public URL produceURLProperty(InjectionPoint ip) {
        try {
            return new URL(produceStringProperty(ip));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
