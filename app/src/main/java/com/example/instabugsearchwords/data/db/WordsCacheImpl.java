package com.example.instabugsearchwords.data.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.instabugsearchwords.R;

public class WordsCacheImpl implements WordsCache {

    private SharedPreferences sharedPreferences;
    private static final String WORDS_KEY = "WORDS_KEY";

    public WordsCacheImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_name), Context.MODE_PRIVATE);
    }

    @Override
    public void saveWords(String wordsResponse) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WORDS_KEY, wordsResponse);
        editor.apply();
    }

    @Override
    public String getWordsFromCache() {
        return sharedPreferences.getString(WORDS_KEY, "");
    }
}
