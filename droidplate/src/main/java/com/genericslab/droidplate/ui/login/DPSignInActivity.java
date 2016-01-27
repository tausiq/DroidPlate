package com.genericslab.droidplate.ui.login;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.genericslab.droidplate.CoreActivity;
import com.genericslab.droidplate.log.Tracer;
import com.genericslab.droidplate.sample.retrofit.IGitAPI;
import com.genericslab.droidplate.sample.retrofit.model.GitResult;
import com.genericslab.droidplate.util.ApiUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Response;


@EActivity(resName = "dp_activity_signin")
public class DPSignInActivity extends CoreActivity {

    @ViewById
    Toolbar toolbar;

    @AfterViews
    void afterViews() {
        setSupportActionBar(toolbar);
        ActionBar supportAB = getSupportActionBar();
        if (supportAB != null) supportAB.setDisplayShowTitleEnabled(false);

        IGitAPI service = ApiUtils.getClient().create(IGitAPI.class);
        Call<GitResult> call = service.getUsersNamedTom("tom");

        call.enqueue(new retrofit2.Callback<GitResult>() {
            @Override
            public void onResponse(Response<GitResult> response) {
                if (response.isSuccess()) {
                    // request successful (status code 200+)
                    GitResult result = response.body();
                    Tracer.d("GitResult size: " + result.getItems().size());
                } else {
                    // request not successful (status code 400+)
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Tracer.e(t, null);
            }
        });

        loadFragment(DPSignInFragment_.builder().build());
    }
}
