package org.ferris.scriptural.window.alert;

import java.util.Arrays;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.ferris.scriptural.window.conf.Conf;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class AlertFrequencyProducer {

    @Inject
    protected Logger log;

    @Produces
    protected AlertFrequency produceAlertFrequency(@Conf("scriptural.alert.frequency") String frequencyStr) {
        String [] strings = splitIntoTwoStrings(frequencyStr);
        int [] ints = convertToInts(strings);
        int [] positive = checkIntsArePositive(ints);
        int [] minmax = checkIntsMinMax(positive);
        return new AlertFrequency(minmax[0], minmax[1]);
    }

    int[] checkIntsMinMax(int [] ints) {
        log.info(String.format("ENTER %s", Arrays.toString(ints)));

        if (ints[0] >= ints[1]) {
            throw new RuntimeException(
                String.format("Error 1st value must be less than last value %s", Arrays.toString(ints))
            );
        }

        return ints;
    }


    int[] checkIntsArePositive(int [] ints) {
        log.info(String.format("ENTER %s", Arrays.toString(ints)));

        for (int x=0; x<=1; x++) {
            if (ints[x] <= 0) {
                throw new RuntimeException(
                    String.format("Error int value %d is not positive", ints[x])
                );
            }
        }
        return ints;
    }

    int[] convertToInts(String [] strings) {
        log.info(String.format("ENTER %s", Arrays.toString(strings)));

        int [] ints = new int[2];

        for (int x=0; x<=1; x++) {
            try {
                ints[x] = Integer.parseInt(strings[x]);
            } catch (Exception e) {
                throw new RuntimeException(
                    String.format("Error String \"%s\" failed to convert to an integer", strings[x])
                );
            }
        }
        return ints;
    }

    String[] splitIntoTwoStrings(String frequencyStr) {
        log.info(String.format("ENTER \"%s\"", frequencyStr));
        String [] tokens = frequencyStr.split(",");
        if (tokens.length != 2) {
            throw new RuntimeException(
                String.format("Error splitting into 2 tokens frequencyStr=\"%s\"", frequencyStr)
            );
        }
        return tokens;
    }
}
