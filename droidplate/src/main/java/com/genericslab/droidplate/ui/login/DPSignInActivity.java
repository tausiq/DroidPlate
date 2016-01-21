package com.genericslab.droidplate.ui.login;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.genericslab.droidplate.CoreActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(resName = "dp_activity_signin")
public class DPSignInActivity extends CoreActivity {

    @ViewById
    Toolbar toolbar;

    @AfterViews
    void afterViews() {
        setSupportActionBar(toolbar);
        ActionBar supportAB = getSupportActionBar();
        if (supportAB != null) supportAB.setDisplayShowTitleEnabled(false);

        loadFragment(DPSignInFragment_.builder().build());
    }
}
