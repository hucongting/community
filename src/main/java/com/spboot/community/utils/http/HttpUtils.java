package com.spboot.community.utils.http;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * http工具类
 */
public class HttpUtils {
    public static String sendOkHttpPost(String url,String param) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build();
        RequestBody body = RequestBody.create(param, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public static String sendOkHttpGet(String url) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String sendOkHttpGet(String url, Map<String,String> param) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build();
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        if (param != null) {
            for(Map.Entry<String, String> p : param.entrySet()) {
                httpBuilder.addQueryParameter(p.getKey(),p.getValue());
            }
        }
        Request request = new Request.Builder().url(httpBuilder.build()).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
