package com.genericslab.droidplate.sample.retrofit;

import com.genericslab.droidplate.sample.retrofit.model.GitResult;
import com.genericslab.droidplate.sample.retrofit.model.Item;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shahab on 1/24/16.
 */
public interface IGitAPI {

    @GET("/search/users")
    Call<GitResult> getUsersNamedTom(@Query("q") String name);

    @POST("/user/create")
    Call<Item> createUser(@Body String name, @Body String email);

    @PUT("/user/{id}/update")
    Call<Item> updateUser(@Path("id") String id , @Body Item user);


}
