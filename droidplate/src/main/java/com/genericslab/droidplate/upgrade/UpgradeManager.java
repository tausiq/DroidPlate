package com.genericslab.droidplate.upgrade;

import com.genericslab.droidplate.BuildConfig;
import com.genericslab.droidplate.CoreActivity;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

/**
 * Created by shahab on 12/28/15.
 */
@EBean
public class UpgradeManager {

    @RootContext
    CoreActivity activity;

    public UpgradeManager() {
    }

    @Background
    void init() {

    }

    @UiThread
    void handleVersionResponse(Upgrade upgrade) {
        int currVersionCode = BuildConfig.VERSION_CODE;
        if (upgrade.getForceUpgradeVersion() > currVersionCode) {
            gotoForceUpdate();
        } else if (upgrade.getMinRequiredVersion() > currVersionCode) {
            gotoRecommendUpdate();
        }
    }

    private void gotoRecommendUpdate() {

    }

    private void gotoForceUpdate() {
//        activity.loadFragment();
    }

}
