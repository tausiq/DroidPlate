package com.genericslab.droidapp;

import com.genericslab.droidplate.CoreActivity;
import com.genericslab.droidplate.ui.drawer.DrawerActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends CoreActivity {

    @AfterViews
    void afterViews() {
        DrawerActivity_.intent(this).start();
    }
}
