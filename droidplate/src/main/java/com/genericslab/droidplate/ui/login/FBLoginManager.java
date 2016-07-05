package com.genericslab.droidplate.ui.login;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.genericslab.droidplate.log.Tracer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shahab on 1/19/16.
 *
 * Config
 * Enable: Single sign on of FB app
 *
 */
public class FBLoginManager {

    Context context;

    LoginButton fbLoginButton;

    private static volatile FBLoginManager instance;

    CallbackManager callbackManager = CallbackManager.Factory.create();

    FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Tracer.d("FB Access token: " + loginResult.getAccessToken().getToken());
            EventBus.getDefault().post(loginResult);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    private FBLoginManager() {

    }

    /**
     * Getter for the login manager.
     * @return The login manager.
     */
    public static FBLoginManager getInstance() {
        if (instance == null) {
            synchronized (FBLoginManager.class) {
                if (instance == null) {
                    instance = new FBLoginManager();
                }
            }
        }

        return instance;
    }

    public FBLoginManager init(Context context, Fragment fragment) {
        return init(context, fragment, getDefaultReadPermissions());
    }

    public FBLoginManager init(Context context, Fragment fragment, List<String> readPermissions) {
        this.context = context;
        fbLoginButton = new LoginButton(context);
        fbLoginButton.setReadPermissions(readPermissions);
        fbLoginButton.setFragment(fragment);
        fbLoginButton.registerCallback(callbackManager, facebookCallback);
        return this;

    }

    private List<String> getDefaultReadPermissions() {
        List<String> ret = new ArrayList<>();
        ret.add("user_friends");
        return ret;
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public void click() {
        fbLoginButton.performClick();
    }

}
