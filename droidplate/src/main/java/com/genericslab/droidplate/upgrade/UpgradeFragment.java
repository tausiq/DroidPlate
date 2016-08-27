package com.genericslab.droidplate.upgrade;


import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.genericslab.droidplate.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(resName = "fragment_upgrade")
public class UpgradeFragment extends Fragment {

    @FragmentArg
    boolean isForceUpgrade;

    @ViewById
    TextView txtMsg;

    public UpgradeFragment() {
        // Required empty public constructor
    }

    @AfterViews
    void afterViews() {
        txtMsg.setText(isForceUpgrade ? R.string.msg_forceUpgrade : R.string.msg_recommendedUpgrade);
    }

    @Click
    void btnUpgrade() {

    }

}
