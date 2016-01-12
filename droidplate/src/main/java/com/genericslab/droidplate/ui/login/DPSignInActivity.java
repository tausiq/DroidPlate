package com.genericslab.droidplate.ui.login;

import com.genericslab.droidplate.CoreActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(resName = "dp_activity_signin")
public class DPSignInActivity extends CoreActivity {

    @AfterViews
    void afterViews() {

        loadFragment(DPLoginFragment_.builder().build());


    }
}
