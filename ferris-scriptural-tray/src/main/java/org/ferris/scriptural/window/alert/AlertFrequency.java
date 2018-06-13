package org.ferris.scriptural.window.alert;

import java.security.SecureRandom;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
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

    // 32,41
    // 32,33,34,35,36,37,38,39,40,41
    public int getRandom() {
        // (41-32 = 9) + 1 = 10
        int size = (max - min) + 1;

        // 0 (inclusive) - 10 (exclusive)
        // or
        // 0 (inclusive) - 9 (inclusive)
        int next = this.random.nextInt(max - min);

        // min + 0 = min (inclusive)
        // min + 9 = max (inclusive)
        return min + next;
    }
}
