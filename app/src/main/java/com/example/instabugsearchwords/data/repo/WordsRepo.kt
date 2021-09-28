package com.example.instabugsearchwords.data.repo

interface WordsRepo {
    fun getWords(callback: WordsCallback)
    fun refreshWords(callback: WordsCallback)
}