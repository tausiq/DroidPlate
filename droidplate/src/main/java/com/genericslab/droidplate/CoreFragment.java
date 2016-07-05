package com.genericslab.droidplate;


import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment
public abstract class CoreFragment extends Fragment {


    public CoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onGenericEvent(Object event) {
        // DO NOT WRITE CODE
    }

}
