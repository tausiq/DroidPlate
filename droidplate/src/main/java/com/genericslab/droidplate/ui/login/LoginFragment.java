package com.genericslab.droidplate.ui.login;


import android.support.v4.app.Fragment;

import com.digits.sdk.android.DigitsAuthButton;
import com.genericslab.droidplate.CoreFragment;
import com.genericslab.droidplate.R;
import com.genericslab.droidplate.app.CoreApplication;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(resName = "fragment_core_login")
public class LoginFragment extends CoreFragment {

    @ViewById
    DigitsAuthButton btnDigits;


    public LoginFragment() {
        // Required empty public constructor
    }

    @AfterViews
    void afterViews() {
        btnDigits.setCallback(((CoreApplication) getActivity().getApplication()).getDigitsAuthCallback());
        btnDigits.setAuthTheme(R.style.Droid_Theme);
    }

}
