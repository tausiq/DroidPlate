package com.genericslab.droidplate.ui.login;

import com.genericslab.droidplate.CoreActivity;
import com.genericslab.droidplate.log.Tracer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;


@EActivity(resName = "dp_activity_signin")
public class DPSignInActivity extends CoreActivity {

    @AfterViews
    void afterViews() {

        loadFragment(DPSignInFragment_.builder().build());
    }
}
