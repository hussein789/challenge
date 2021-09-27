package com.example.instabugsearchwords.data.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.instabugsearchwords.R;

public interface WordsCache {
    void saveWords(String wordsResponse);
    String getWordsFromCache();
}

