package com.glg.networktest;

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
