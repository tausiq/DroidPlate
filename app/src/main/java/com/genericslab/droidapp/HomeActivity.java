package com.genericslab.droidapp;

import android.app.Activity;

import com.genericslab.droidplate.ui.drawer.DrawerActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_home)
public class HomeActivity extends Activity {

    @AfterViews
    void afterViews() {
        DrawerActivity_.intent(this).start();
        finish();
    }
}
