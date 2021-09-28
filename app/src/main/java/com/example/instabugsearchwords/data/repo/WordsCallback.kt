package com.example.instabugsearchwords.data.repo;

public interface WordsCallback {
    void onSuccess(String response);
    void onFail(String message);
}
