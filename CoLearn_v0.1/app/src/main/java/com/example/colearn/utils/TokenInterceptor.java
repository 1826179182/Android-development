package com.example.colearn.utils;

import static com.xuexiang.xui.XUI.getContext;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * token拦截器
 */

public class TokenInterceptor implements Interceptor {

    private String token; //用于添加的请求头

    @Override
    public Response intercept(Chain chain) throws IOException {

        token = SPUtils.getString("token", "", getContext());

        Request request;
        if (token == null) {
//            request = chain.request();
            request = chain.request()
                    .newBuilder()
                    .build();
        } else {
            request = chain.request()
                    .newBuilder()
                    .addHeader("token", token)
                    .build();
        }
        return chain.proceed(request);
    }

}