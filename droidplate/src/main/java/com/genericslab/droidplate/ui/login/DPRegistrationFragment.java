package com.genericslab.droidplate.ui.login;


import android.support.v4.app.Fragment;

import com.genericslab.droidplate.CoreActivity;
import com.genericslab.droidplate.CoreFragment;
import com.genericslab.droidplate.ui.common.DPWebViewFragment_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(resName = "dp_fragment_registration")
public class DPRegistrationFragment extends CoreFragment {


    public DPRegistrationFragment() {
        // Required empty public constructor
    }

    @Click
    void txtTos() {
        ((CoreActivity) getActivity()).loadChildFragment(DPWebViewFragment_.builder().url("http://www.apache.org/licenses/LICENSE-2.0").build());
    }

}
