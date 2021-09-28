package com.example.instabugsearchwords.data.repo

import com.example.instabugsearchwords.data.db.WordsCache
import com.example.instabugsearchwords.data.network.NetworkUtils
import com.example.instabugsearchwords.utils.Utils
import java.io.IOException

class WordsRepoImpl(private var networkUtils: NetworkUtils, private var wordsCache: WordsCache) : WordsRepo {

    override fun getWords(callback: WordsCallback) {
        val cachedWords = wordsCache.wordsFromCache
        if (cachedWords == null || cachedWords.isEmpty()) {
            Utils.executorService.execute {
                val searchUrl = networkUtils.buildUrl()
                try {
                    val response = networkUtils.getResponseFromHttpUrl(searchUrl)
                    wordsCache.saveWords(response)
                    callback.onSuccess(response)
                } catch (e: IOException) {
                    e.printStackTrace()
                    callback.onFail(e.message)
                }
            }
        } else {
            callback.onSuccess(cachedWords)
        }
    }

    override fun refreshWords(callback: WordsCallback) {
        Utils.executorService.execute {
            val searchUrl = networkUtils.buildUrl()
            try {
                val response = networkUtils.getResponseFromHttpUrl(searchUrl)
                wordsCache.saveWords(response)
                callback.onSuccess(response)
            } catch (e: IOException) {
                e.printStackTrace()
                callback.onFail(e.message)
            }
        }
    }
}