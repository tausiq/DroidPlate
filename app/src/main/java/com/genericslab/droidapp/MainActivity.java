package com.genericslab.droidapp;

import android.os.Bundle;

import com.genericslab.droidplate.CoreActivity;
import com.genericslab.droidplate.ui.drawer.DrawerActivity_;

public class MainActivity extends CoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        DrawerActivity_.intent(this).start();
    }
}
