package com.MOHAMEDNADER.retail.webServices;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static final String BASE_URL = "http://retail.amit-learning.com/api/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){

        if(retrofit == null){


            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(14, TimeUnit.SECONDS)
                    .readTimeout(14,TimeUnit.SECONDS)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

        }

        return retrofit;

    }

}
