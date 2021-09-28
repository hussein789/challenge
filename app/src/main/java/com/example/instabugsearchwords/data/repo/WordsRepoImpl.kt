package com.example.instabugsearchwords.data.repo;

import com.example.instabugsearchwords.MyApplication;
import com.example.instabugsearchwords.data.db.WordsCache;
import com.example.instabugsearchwords.data.network.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class WordsRepoImpl implements WordsRepo {

    NetworkUtils networkUtils;
    WordsCache wordsCache;


    public WordsRepoImpl(NetworkUtils networkUtils, WordsCache cache) {
        this.networkUtils = networkUtils;
        this.wordsCache = cache;
    }


    @Override
    public void getWords(WordsCallback callback) {
        String cachedWords = wordsCache.getWordsFromCache();
        if (cachedWords == null || cachedWords.isEmpty()) {
            String response = null;
                MyApplication.executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        URL searchUrl = networkUtils.buildUrl();
                        try {
                            String response = networkUtils.getResponseFromHttpUrl(searchUrl);
                            wordsCache.saveWords(response);
                            callback.onSuccess(response);
                        } catch (IOException e) {
                            e.printStackTrace();
                            callback.onFail(e.getMessage());
                        }
                    }
                });
        } else {
            callback.onSuccess(cachedWords);
        }
    }

    @Override
    public void refreshWords(WordsCallback callback) {
        MyApplication.executorService.execute(new Runnable() {
            @Override
            public void run() {
                URL searchUrl = networkUtils.buildUrl();
                try {
                    String response = networkUtils.getResponseFromHttpUrl(searchUrl);
                    wordsCache.saveWords(response);
                    callback.onSuccess(response);
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFail(e.getMessage());
                }
            }
        });
    }
}
