package com.genericslab.droidplate.upgrade;

/**
 * Created by shahab on 12/28/15.
 */
public class Upgrade {

    private final String COL_MIN_REQUIRED_VERSION = "minRequiredVersion";
    private final String COL_TARGET = "target";
    private final String COL_FORCE_UPGRADE_VERSION = "forceUpgradeVersion";

    public Upgrade() {}

    public int getMinRequiredVersion() {
        return 1;
    }

    public int getTarget() {
        return 2;
    }

    public int getForceUpgradeVersion() {
        return 3;
    }

}
