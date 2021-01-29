package com.bentley.personalstudy.data.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static ApiManager apiManager;

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }


    public ApiService getService() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logger);

        httpClient.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .header("UserBody-Agent", "android")
                    .addHeader("Content-Type", "application/json")
                    .build();

            return chain.proceed(request);
        });


        OkHttpClient client = httpClient.build();

        String API_URL = "https://api.itbook.store/1.0/";
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
