package com.genericslab.droidplate.ui.login;

import com.genericslab.droidplate.CoreActivity;
import com.genericslab.droidplate.log.Tracer;
import com.genericslab.droidplate.sample.retrofit.IGitAPI;
import com.genericslab.droidplate.sample.retrofit.model.GitResult;
import com.genericslab.droidplate.util.ApiUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@EActivity(resName = "dp_activity_signin")
public class DPSignInActivity extends CoreActivity {

    @AfterViews
    void afterViews() {

        IGitAPI service = ApiUtils.createService(IGitAPI.class);
        Call<GitResult> call = service.getUsersNamedTom("tom");

        call.enqueue(new Callback<GitResult>() {
            @Override
            public void onResponse(Call<GitResult> call, Response<GitResult> response) {
                if (response.isSuccessful()) {
                    // request successful (status code 200+)
                    GitResult result = response.body();
                    Tracer.d("GitResult size: " + result.getItems().size());
                } else {
                    // request not successful (status code 400+)
                }
            }

            @Override
            public void onFailure(Call<GitResult> call, Throwable t) {
                Tracer.e(t, null);
            }
        });

        loadFragment(DPSignInFragment_.builder().build());
    }
}
