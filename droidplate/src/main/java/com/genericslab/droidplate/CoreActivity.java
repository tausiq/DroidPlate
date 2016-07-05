package com.genericslab.droidplate;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.genericslab.droidplate.config.Config;
import com.genericslab.droidplate.helper.Validate;
import com.genericslab.droidplate.log.Tracer;
import com.genericslab.droidplate.ui.dialog.LockProgressDialog;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by shahab on 12/17/15.
 *
 * Every Activity should extend from this
 */
public abstract class CoreActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    protected final String TRACE_TAG = Config.TRACE_TAG + "CoreActivity";

    protected LockProgressDialog dialog;

    protected String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tracer.v(this.getClass().getSimpleName() + " started");

        setTag(this.getClass().getSimpleName());
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        homeAsUpByBackStack();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void displayLockProgress() {
        FragmentManager fm = getSupportFragmentManager();
        if (dialog != null) dismissLockProgress();
        dialog = new LockProgressDialog();
        dialog.show(fm, "LockProgressDialog");
    }

    public void dismissLockProgress() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    /**
     * Load fragment by replacing all previous fragments
     * @param fragment
     */
    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // clear back stack
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
        FragmentTransaction t = fragmentManager.beginTransaction();
        t.replace(R.id.mainView, fragment, "main");
        fragmentManager.popBackStack();
        // TODO: we have to allow state loss here
        // since this function can get called from an AsyncTask which
        // could be finishing after our app has already committed state
        // and is about to get shutdown.  What we *should* do is
        // not commit anything in an AsyncTask, but that's a bigger
        // change than we want now.
        t.commitAllowingStateLoss();
    }

    /**
     * Load Fragment on top of other fragments
     * @param fragment
     */
    public void loadChildFragment(Fragment fragment) {
        Validate.notNull(fragment);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainView, fragment, "main")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onBackStackChanged() {
        homeAsUpByBackStack();
    }

    private void homeAsUpByBackStack() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) return;
        if (backStackEntryCount > 0) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    finish();
                } else {
                    getSupportFragmentManager().popBackStack();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
