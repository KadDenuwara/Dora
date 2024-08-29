package com.example.dora.activityAI;

public interface ResponseCallback {
    void onResponse(String response);
    void onError(Throwable throwable);
}
