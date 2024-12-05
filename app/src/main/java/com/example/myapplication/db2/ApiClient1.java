package com.example.myapplication.db2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient1 {
    // 默认的 BASE_URL，如果没有设置新的 URL，使用这个值
    private static String BASE_URL = "http://8.155.30.228:8000/"; // 默认值
    private static Retrofit retrofit;

    // 获取 Retrofit 实例
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            buildRetrofit(); // 如果未初始化 Retrofit，则构建它
        }
        return retrofit;
    }

    // 创建 Retrofit 实例
    private static void buildRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)  // 使用当前的 BASE_URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // 更新 BASE_URL
    public static void updateBaseUrl(String newBaseUrl) {
        BASE_URL = newBaseUrl; // 更新 BASE_URL
        buildRetrofit();  // 重新构建 Retrofit 实例
    }
}
