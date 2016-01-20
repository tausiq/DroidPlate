package com.genericslab.droidplate.app;

import android.app.Application;
import android.hardware.Camera;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.facebook.FacebookSdk;
import com.genericslab.droidplate.R;
import com.genericslab.droidplate.config.Config;
import com.genericslab.droidplate.log.Tracer;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.parse.Parse;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;
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

    private AuthCallback digitsAuthCallback;

    public static synchronized CoreApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        LOGICAL_DENSITY = getResources().getDisplayMetrics().density;

        // TODO: Add Fabric.io all components

        init();
    }

    public static float getLogicalDensity() {
        return LOGICAL_DENSITY;
    }

    protected void init() {
        configureCalligraphy();
        configureTracer();
        configureParse();
        configureFabric();
        configureIconify();
        configureSocial();
    }

    private void configureSocial() {
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    private void configureIconify() {
        Iconify.with(new FontAwesomeModule());
    }

    private void configureFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_key), getString(R.string.twitter_secret));
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics(), new TwitterCore(authConfig), new Digits())
                .debuggable(Config.DEBUG)
                .build();
        Fabric.with(fabric);

        digitsAuthCallback = new AuthCallback() {
            @Override
            public void success(DigitsSession digitsSession, String phoneNumber) {
                /*
                session.getPhoneNumber(); OR, phoneNumber
                TwitterAuthToken authToken = (TwitterAuthToken) session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                session.getId();
                 */
            }

            @Override
            public void failure(DigitsException e) {
                //
            }
        };

    }

    public AuthCallback getDigitsAuthCallback() {
        return digitsAuthCallback;
    }

    private void configureParse() {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
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

    private Camera camera;

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

}
