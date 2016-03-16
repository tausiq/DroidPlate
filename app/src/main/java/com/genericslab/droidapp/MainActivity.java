package com.genericslab.droidapp;

import com.genericslab.droidplate.CoreActivity;
import com.genericslab.droidplate.config.Config;
import com.genericslab.droidplate.ui.drawer.DrawerActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Trace;

@EActivity(R.layout.activity_main)
public class MainActivity extends CoreActivity {

    protected final String TRACE_TAG = Config.TRACE_TAG + "MainActivity";

    @Trace(tag = TRACE_TAG)
    @AfterViews
    void afterViews() {
        DrawerActivity_.intent(this).start();
        finish();
    }
}
