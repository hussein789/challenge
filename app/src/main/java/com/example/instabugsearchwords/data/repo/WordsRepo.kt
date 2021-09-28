package com.example.instabugsearchwords.data.repo;


import com.example.instabugsearchwords.data.db.WordsCache;
import com.example.instabugsearchwords.data.network.NetworkUtils;

import java.net.URL;

public interface WordsRepo{
    void getWords(WordsCallback callback);
    void refreshWords(WordsCallback callback);
}


