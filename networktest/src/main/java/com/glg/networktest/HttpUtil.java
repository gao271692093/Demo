package com.glg.networktest;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    /**
     * 需要发送Http请求的时候可以封装类似的工具类
     * 在需要发起一条HTTP请求的时候就可以这样写：
     * String address = "http://www.baidu.com";
     * String response = "HttpUtil.sendHttpRequest(address);
     * */
    /*
    public static String sendHttpResquest(String address) {
        HttpURLConnection connection = null;
        try{
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while((line = reader.readLine())!=null) {
                response.append(line);
            }
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }*/

    //第一种方式
    public static void sendHttpResquest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine())!=null) {
                        response.append(line);
                    }
                    if(listener != null) {
                        //回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if(listener != null) {
                        //回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if(connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    //使用的时候可以用以下方式 使用
    /*
        HttpUtil.sendHttpResquest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                //在这里根据返回内容执行具体的逻辑
            }

            @Override
            public void onError(Exception e) {
                //在这里对异常情况进行处理
            }
        });
     */

    //第二种方式
    public static void sendOkHttpRequest(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
    //使用：
    /*
    HttpUtil.sendOkHttpRequest("http://www.baidu.com", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //在这里对异常情况进行处理
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //得到服务器返回的具体内容
                String responseData = response.body().string();
            }
        });
     */
}
