package com.example.instabugsearchwords.data.repo

interface WordsCallback {
    fun onSuccess(response: String?)
    fun onFail(message: String?)
}