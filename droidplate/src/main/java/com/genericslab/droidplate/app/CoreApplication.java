package com.genericslab.droidplate.app;

import android.hardware.Camera;
import android.provider.Settings;
import android.support.multidex.MultiDexApplication;

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
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.Trace;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by shahab on 12/17/15.
 */
@EApplication
public abstract class CoreApplication extends MultiDexApplication {

    private final String TRACE_TAG = Config.TRACE_TAG + "CoreApplication";

    /**
     * Logical density of this device
     */
    private static float LOGICAL_DENSITY;

    private static CoreApplication sInstance;

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

    @Trace(tag = TRACE_TAG)
    protected void init() {
        configureCalligraphy();
        configureTracer();
        configureFabric();
        configureIconify();
        configureSocial();
        configureRetrofit();
        configureStetho();
    }

    private void configureStetho() {
        //Stetho.initializeWithDefaults(this);
    }

    private void configureRetrofit() {

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


    private Camera camera;

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getUniqueDeviceId() {
        return Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
