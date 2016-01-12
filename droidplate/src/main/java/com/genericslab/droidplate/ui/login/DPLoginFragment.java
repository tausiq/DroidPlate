package com.genericslab.droidplate.ui.login;


import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.digits.sdk.android.DigitsAuthButton;
import com.genericslab.droidplate.CoreFragment;
import com.genericslab.droidplate.R;
import com.genericslab.droidplate.app.CoreApplication;
import com.genericslab.droidplate.app.DroidPrefs_;
import com.genericslab.droidplate.config.Config;
import com.genericslab.droidplate.helper.UIUtils;
import com.genericslab.droidplate.helper.Validate;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(resName = "dp_fragment_login")
public class DPLoginFragment extends CoreFragment {

    @ViewById
    DigitsAuthButton btnDigits;
    @ViewById
    TextInputLayout txtEmailLayout;
    @ViewById
    TextInputLayout txtPasswordLayout;
    @ViewById
    EditText txtEmail;
    @ViewById
    EditText txtPassword;
    @ViewById
    Button btnLogin;

    @Pref
    DroidPrefs_ prefs;


    public DPLoginFragment() {
        // Required empty public constructor
    }

    @AfterViews
    void afterViews() {
        if (prefs.isLoggedIn().get()) gotoNextScreen();

        btnDigits.setCallback(((CoreApplication) getActivity().getApplication()).getDigitsAuthCallback());
        btnDigits.setAuthTheme(R.style.Droid_Theme);

        // call this method after initialization, thus button state can be in correct state
        afterTextChange(txtEmail);

        if (Config.FILL_DATA) {
            txtEmail.setText(Config.DEFAULT_EMAIL);
            txtPassword.setText("asdfghkwoed");
        }

        if (Config.SKIP) {
            btnLogin();
        }
    }

    @Click
    void btnLogin() {
        if (checkValidation()) {
            prefs.isLoggedIn().put(true);
            gotoNextScreen();
        }
    }

    private void gotoNextScreen() {
        try {
            Intent intent = new Intent(getActivity(), Class.forName(Config.MAIN_ACTIVITY));
            intent.putExtra("feedUrl", "http://feeds.feedburner.com/tedtalks_video ");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Click
    void btnRegistration() {
        UIUtils.alert(getActivity(), "Please contact administrator for username and password");
    }

    @Click
    void btnFacebook() {
        prefs.isLoggedIn().put(true);
        gotoNextScreen();
    }

    @Click
    void btnGoogle() {
        prefs.isLoggedIn().put(true);
        gotoNextScreen();
    }

    @Click
    void btnTwitter() {
        prefs.isLoggedIn().put(true);
        gotoNextScreen();
    }

    @Click
    void btnDigitsPlaceholder() {
        prefs.isLoggedIn().put(true);
        gotoNextScreen();
    }

    private boolean checkValidation() {

        return Validate.checkRequiredField(txtEmailLayout) &&
                Validate.isValidEmail(txtEmailLayout) &&
                Validate.checkRequiredField(txtPasswordLayout) &&
                Validate.isValidPassword(txtPasswordLayout);
    }

    @AfterTextChange(resName = {"txtEmail", "txtPassword"})
    void afterTextChange(TextView txtView) {
        if (txtEmail.getText().length() != 0 && txtPassword.getText().length() != 0) {
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }
    }
}