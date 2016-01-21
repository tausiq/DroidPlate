package com.genericslab.droidplate.ui.login;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.genericslab.droidplate.R;

/**
 * Created by shahab on 1/21/16.
 */
public class DPSignInAdapter extends FragmentPagerAdapter {

    Resources resources;

    private static final int POS_LOGIN = 0;
    private static final int POS_REGISTRATION = 1;

    public DPSignInAdapter(FragmentManager fm, Resources resources) {
        super(fm);
        this.resources = resources;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POS_LOGIN:
                return DPLoginFragment_.builder().build();
            case POS_REGISTRATION:
                return DPRegistrationFragment_.builder().build();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case POS_LOGIN:
                return resources.getString(R.string.label_login);
            case POS_REGISTRATION:
                return resources.getString(R.string.label_registration);
            default:
                return super.getPageTitle(position);
        }
    }
}
