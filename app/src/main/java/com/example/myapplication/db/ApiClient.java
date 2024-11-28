package com.example.myapplication.db;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static String BASE_URL = "http://192.168.31.140:8001/"; // 默认值
    private static Retrofit retrofit;

    // 更新 BASE_URL
    public static void updateBaseUrl(String newBaseUrl) {
        BASE_URL = newBaseUrl;
        retrofit = null; // 重置 retrofit 实例，确保下一次使用新的 BASE_URL
    }

    // 获取 Retrofit 实例
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
