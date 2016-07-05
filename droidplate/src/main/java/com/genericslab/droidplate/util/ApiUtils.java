package com.genericslab.droidplate.util;

import com.genericslab.droidplate.config.Config;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shahab on 1/24/16.
 */
public class ApiUtils {

    private static String baseUrl = "https://api.github.com/";
    private static Retrofit retroClient;

    private static OkHttpClient getOkHttp() {
        // Define the interceptor, add authentication headers
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("User-Agent", "Droid-app")
                        .addHeader("Content-Type", "application/json;charset=utf-8")
                        .addHeader("Accept", "application/json;charset=utf-8")
                        .build();
                return chain.proceed(newRequest);
            }
        };

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
//                .addNetworkInterceptor(new StethoInterceptor())

                .build();
    }

    private static void init() {
        retroClient = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttp())
                .addConverterFactory(GsonConverterFactory.create(getGson()))

//               Self-signing
                .client(getOkHttp())
                .build();
    }

    private static Retrofit getClient() {
        if (retroClient == null) {
            init();
        }
        return retroClient;
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .setDateFormat(Config.ISO_FORMAT)

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

    public static <S> S createService(Class<S> serviceClass) {
        return getClient().create(serviceClass);
    }
}
