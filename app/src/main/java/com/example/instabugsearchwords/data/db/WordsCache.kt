package com.example.instabugsearchwords.data.db

interface WordsCache {
    fun saveWords(wordsResponse: String?)
    val wordsFromCache: String?
}