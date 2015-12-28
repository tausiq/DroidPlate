package com.genericslab.droidapp;


import android.support.v4.app.Fragment;

import com.digits.sdk.android.DigitsAuthButton;
import com.genericslab.droidplate.ui.login.CoreLoginFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_core_login)
public class LoginFragment extends CoreLoginFragment {

    @ViewById
    DigitsAuthButton btnDigits;


    public LoginFragment() {
        // Required empty public constructor
    }

    @AfterViews
    void afterViews() {
        btnDigits.setCallback(((App) getActivity().getApplication()).getDigitsAuthCallback());
        btnDigits.setAuthTheme(R.style.AppTheme);
    }

}
