package com.example.simon.gratisgoder.API;

/**
 * Created by Tobias on 30-10-2017.
 */
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Service {

    public static final String BASE_URL = "http:gratisgoder.dk";

    private static Service instance;

    private Retrofit retrofit;
    private OkHttpClient httpClient;

    public static <S> S createService(Class<S> serviceClass) {
        if (instance == null) {
            instance = new Service();
        }
        return instance.getretrofit().create(serviceClass);
    }

    private Retrofit getretrofit() {
        if (retrofit == null) {
            // Build Retrofit object
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .client(getHttpClient())
                    .build();
        }

        return retrofit;
    }

    private OkHttpClient getHttpClient() {
        if (httpClient == null) {

            // Set Logging interceptor
            HttpLoggingInterceptor log = new HttpLoggingInterceptor();
            log.setLevel(HttpLoggingInterceptor.Level.BODY);


            // Create Httpclient
            OkHttpClient.Builder builder = new OkHttpClient
                    .Builder()
                    .addInterceptor(log)
                    .connectionPool(new ConnectionPool())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS);

            httpClient = builder.build();
        }
        return httpClient;
    }
}