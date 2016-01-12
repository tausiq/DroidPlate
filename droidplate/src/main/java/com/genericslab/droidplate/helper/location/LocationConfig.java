package com.genericslab.droidplate.helper.location;

/**
 * Created by shahab on 1/1/16.
 */
public class LocationConfig {

    public enum Frequency {
        ONE_TIME, PERIODIC
    }

    private Frequency frequency;

    // TODO: do it in builder pattern
    public LocationConfig(Frequency frequency) {
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }
}
