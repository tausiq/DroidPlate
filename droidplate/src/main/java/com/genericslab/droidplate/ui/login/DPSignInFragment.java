package com.genericslab.droidplate.ui.login;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.genericslab.droidplate.CoreFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(resName = "dp_tab_pager")
public class DPSignInFragment extends CoreFragment implements IPager {

    @ViewById
    TabLayout tabs;
    @ViewById
    ViewPager pager;

    @FragmentArg()
    int selectedTab = 0;


    public DPSignInFragment() {
        // Required empty public constructor
    }

    @AfterViews
    void afterViews() {
        pager.setAdapter(new DPSignInAdapter(getChildFragmentManager(), getResources()));
        tabs.setupWithViewPager(pager);
        pager.setCurrentItem(selectedTab, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        pager.setCurrentItem(item, smoothScroll);
    }
}
