package com.example.goldscavenging.Network;

import com.example.goldscavenging.Local_DB.LocalSession;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static final String TAG = "ApiClient";
    public static String BASE_URL = "http://goldscavenging.herokuapp.com/api/";
    public static Retrofit getClient(String baseUrl)
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()

                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.MINUTES)
                .writeTimeout(60, TimeUnit.MINUTES)
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient().setLenient()
                .create();

        return new Retrofit.
                Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
