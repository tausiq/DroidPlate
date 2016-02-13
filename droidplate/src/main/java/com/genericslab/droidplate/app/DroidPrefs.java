package com.genericslab.droidplate.app;

import com.genericslab.droidplate.config.Config;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by shahab on 1/8/16.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface DroidPrefs {

    @DefaultBoolean(true)
    boolean isFirstLaunch();

    @DefaultBoolean(false)
    boolean isLoggedIn();

    @DefaultString(Config.DEFAULT_USER_NAME)
    String userName();

    @DefaultString(Config.DEFAULT_EMAIL)
    String userEmail();

    @DefaultString("")
    String accessToken();

    @DefaultString("")
    String firstName();

    @DefaultString("")
    String lastName();

    @DefaultString("")
    String password();
}
