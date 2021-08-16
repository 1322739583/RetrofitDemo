package com.example.retrofitdemo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static final String BASE_URL = "https://api.github.com/";
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL) // 设置 网络请求 Url
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();



    public static <S> S createService(Class<S> serviceClass) {
        if (!httpBuilder.interceptors().contains(loggingInterceptor)) {
            httpBuilder.addInterceptor(loggingInterceptor);
            httpBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            //不要提交令牌到github,令牌会失效
                            .addHeader("Authorization", "token "+"fc2295058bf796dc65365f94f59d15f131dc691f")
                      //      .header("Accept", "application/json")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
            builder = builder.client(httpBuilder.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }


}
