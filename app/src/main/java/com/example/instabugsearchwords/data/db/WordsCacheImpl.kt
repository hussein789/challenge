package com.example.instabugsearchwords.data.db

import android.content.Context
import android.content.SharedPreferences
import com.example.instabugsearchwords.R

class WordsCacheImpl(context: Context) : WordsCache {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_name),
        Context.MODE_PRIVATE
    )

    override fun saveWords(wordsResponse: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(WORDS_KEY, wordsResponse)
        editor.apply()
    }

    override val wordsFromCache: String?
        get() = sharedPreferences.getString(WORDS_KEY, "")

    companion object {
        private const val WORDS_KEY = "WORDS_KEY"
    }

}