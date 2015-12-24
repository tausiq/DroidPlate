package com.genericslab.droidplate.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.genericslab.droidplate.R;
import com.genericslab.droidplate.config.Config;
import com.genericslab.droidplate.log.Tracer;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by shahab on 12/17/15.
 */
public abstract class CoreApplication extends Application {

    /**
     * Logical density of this device
     */
    private static float LOGICAL_DENSITY;

    private static CoreApplication sInstance;

    private RequestQueue mRequestQueue;

    public static synchronized CoreApplication getInstance() { return sInstance; }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        LOGICAL_DENSITY = getResources().getDisplayMetrics().density;

        // TODO: Add Fabric.io all components

        init();
    }

    public static float getLogicalDensity() { return LOGICAL_DENSITY; }

    protected void init() {
        configureCalligraphy();
        configureTracer();
    }

    private void configureTracer() {
        Tracer.init();
    }

    private void configureCalligraphy() {
        CalligraphyConfig
                .initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(getString(Config.DEFAULT_FONT_PATH_RES))
                        .setFontAttrId(R.attr.fontPath)
                        .build());
    }

    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

}
