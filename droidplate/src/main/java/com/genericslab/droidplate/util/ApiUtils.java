package com.genericslab.droidplate.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


/**
 * Created by shahab on 1/24/16.
 */
public class ApiUtils {

    private static String baseUrl = "https://api.github.com";
    private static Retrofit retroClient;

    public static String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    private static void initOkHttp() {
        OkHttpClient okClient = new OkHttpClient();

        // Define the interceptor, add authentication headers
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request newRequest = chain.request().newBuilder()
                        .addHeader("User-Agent", "Droid-app")
                        .build();
                return chain.proceed(newRequest);
            }
        };

        okClient.interceptors().add(interceptor);
    }

    public static void init() {
        initOkHttp();
        retroClient = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
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
                .create();
    }
}
