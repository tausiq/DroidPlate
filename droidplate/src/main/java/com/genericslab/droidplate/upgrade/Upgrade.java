package com.genericslab.droidplate.upgrade;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by shahab on 12/28/15.
 */
@ParseClassName("Upgrade")
public class Upgrade extends ParseObject {

    private final String COL_MIN_REQUIRED_VERSION = "minRequiredVersion";
    private final String COL_TARGET = "target";
    private final String COL_FORCE_UPGRADE_VERSION = "forceUpgradeVersion";

    public Upgrade() {}

    public int getMinRequiredVersion() {
        return getInt(COL_MIN_REQUIRED_VERSION);
    }

    public int getTarget() {
        return getInt(COL_TARGET);
    }

    public int getForceUpgradeVersion() {
        return getInt(COL_FORCE_UPGRADE_VERSION);
    }

}
