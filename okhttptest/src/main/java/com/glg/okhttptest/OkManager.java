package com.glg.okhttptest;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Description:
 *
 * @package: com.glg.okhttptest
 * @className: OkManager
 * @author: gao
 * @date: 2020/8/25 23:36
 */
public class OkManager {
    private static OkManager manager;
    public static OkManager getInstance() {
        OkManager instance = null;
        if (manager == null) {
            synchronized (OkManager.class) {                //同步代码块
                if (instance == null) {
                    instance = new OkManager();
                    manager = instance;
                }
            }
        }
        return instance;
    }

    public static void postFile(String url, RequestBody requestBody , okhttp3.Callback callback)
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
