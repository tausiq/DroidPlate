package com.genericslab.droidplate.util;

import com.genericslab.droidplate.service.network.SelfSigningClientBuilder;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import io.realm.RealmObject;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by shahab on 1/24/16.
 */
public class ApiUtils {

    private static String baseUrl = "https://api.github.com/";
    private static Retrofit retroClient;

    public static String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    private static OkHttpClient getOkHttp() {
        // Define the interceptor, add authentication headers
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("User-Agent", "Droid-app")
                        .build();
                return chain.proceed(newRequest);
            }
        };

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
//                .addNetworkInterceptor(new StethoInterceptor())

                .build();
    }

    public static void init() {
        retroClient = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .client(getOkHttp())
                .addConverterFactory(GsonConverterFactory.create(getGson()))

//               Self-signing
                .client(SelfSigningClientBuilder.createClient())
                .build();
    }

    public static Retrofit getClient() {
        if (retroClient == null) {
            init();
        }
        return retroClient;
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .setDateFormat(ISO_FORMAT)

                // required for realm.io
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
    }
}
