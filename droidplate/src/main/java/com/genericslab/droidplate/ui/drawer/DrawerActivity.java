package com.genericslab.droidplate.ui.drawer;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.genericslab.droidplate.CoreActivity;
import com.genericslab.droidplate.R;
import com.genericslab.droidplate.app.DroidPrefs_;
import com.genericslab.droidplate.helper.glide.ApGlideSettings;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.mopub.mobileads.MoPubView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by shahab on 12/28/15.
 */
@EActivity(resName = "activity_drawer")
public class DrawerActivity extends CoreActivity implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById Toolbar toolbar;
    @ViewById FloatingActionButton fab;
    @ViewById DrawerLayout drawerLayout;
    @ViewById NavigationView navView;
    @ViewById
    MoPubView mopubAdd;

    TextView txtName;
    TextView txtEmail;
    ImageView avatar;

    @Pref
    DroidPrefs_ prefs;

    @AfterViews
    void afterViews() {
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        txtName = (TextView) navView.getHeaderView(0).findViewById(R.id.txtName);
        txtEmail = (TextView) navView.getHeaderView(0).findViewById(R.id.txtEmail);
        avatar = (ImageView) navView.getHeaderView(0).findViewById(R.id.avatar);

        Glide.with(this)
                .load("https://cdn1.iconfinder.com/data/icons/free-98-icons/32/user-128.png")
                .placeholder(new IconDrawable(this, FontAwesomeIcons.fa_user))
                .error(new IconDrawable(this, FontAwesomeIcons.fa_user))
                .diskCacheStrategy(ApGlideSettings.AP_DISK_CACHE_STRATEGY)
                .fitCenter()
                .dontAnimate()
                .into(avatar);


        mopubAdd.setAdUnitId("d4a0aba637d64a9f9a05a575fa757ac2");
        mopubAdd.loadAd();

    }

    @Override
    protected void onDestroy() {
        mopubAdd.destroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_settings) {

        }

        drawerLayout.closeDrawers();
        return true;
    }
}
