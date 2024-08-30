package com.demo.dora.activityAI;

public interface ResponseCallback {
    void onResponse(String response);
    void onError(Throwable throwable);
}
