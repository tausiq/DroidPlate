package com.genericslab.droidplate.config;

import com.genericslab.droidplate.R;

/**
 * Created by shahab on 12/17/15.
 */
public class Config {

    public static final boolean DEBUG = false;

    public static final boolean FILL_DATA = false;

    public static final boolean SECRET_BUILD = false;

    public static final boolean NETWORK_DELAY = false;

    public static final boolean SKIP = false;

    /*
    Configuration
     */

    public static final String LOG_TAG = "debug-app";

    //
    public static final long TASK_TIMEOUT_MILLIS = 10 * 1000;   // 10 seconds

    public static final int REQUEST_TIMEOUT_MILLIS = 10 * 1000; // 10 seconds

    public static final int NETWORK_DELAY_SECONDS = 3;

    // Calligraphy - custom fonts
    public static final int DEFAULT_FONT_PATH_RES = R.string.fonts_noto_sans_regular;

//    TODO: How frequently log files will be generated, currently for every 1 min, it creates one file
}
