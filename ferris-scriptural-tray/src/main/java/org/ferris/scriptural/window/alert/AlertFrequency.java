package org.ferris.scriptural.window.alert;

import java.security.SecureRandom;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class AlertFrequency {
    protected int min, max;
    protected SecureRandom random;

    public AlertFrequency(int min, int max) {
        this.min = min;
        this.max = max;
        this.random = new SecureRandom();
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int pick() {
        // Let:
        // frequency be: 32,41
        //
        // Then:
        // max=41
        // min=32
        // max-min=9
        // nextInt(9 + 1) returns lowest 0 and highest 9
        int next = this.random.nextInt((max - min) + 1);

        // min + 0 = min (inclusive)
        // min + 9 = max (inclusive)
        return min + next;
    }
}
