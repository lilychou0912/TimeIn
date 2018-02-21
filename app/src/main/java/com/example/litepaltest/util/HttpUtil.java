package com.example.litepaltest.util;

import android.app.DownloadManager;

/**
 * Created by ASUS on 2018/2/21.
 */

public class HttpUtil {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);

    }
}
