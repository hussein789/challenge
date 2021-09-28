package com.example.instabugsearchwords.domain.di

import android.content.Context
import com.example.instabugsearchwords.data.db.WordsCache
import com.example.instabugsearchwords.data.db.WordsCacheImpl
import com.example.instabugsearchwords.data.network.NetworkUtils
import com.example.instabugsearchwords.data.network.NetworkUtilsImpl
import com.example.instabugsearchwords.data.repo.WordsRepo
import com.example.instabugsearchwords.data.repo.WordsRepoImpl
import com.example.instabugsearchwords.domain.get_words.GetWordsUseCase
import com.example.instabugsearchwords.domain.get_words.GetWordsUseCaseImpl
import com.example.instabugsearchwords.presentation.WordsListViewModelFactory

class ServiceLocator private constructor() {

    private fun provideWordsCache(context: Context?): WordsCache {
        return WordsCacheImpl(context!!)
    }

    private fun provideNetworkUtils(): NetworkUtils {
        return NetworkUtilsImpl()
    }

    private fun provideWordsRepo(context: Context?): WordsRepo {
        val networkUtils = provideNetworkUtils()
        val wordsCache = provideWordsCache(context)
        return WordsRepoImpl(networkUtils, wordsCache)
    }

    private fun provideGetWordsUseCase(context: Context?): GetWordsUseCase {
        val repo = provideWordsRepo(context)
        return GetWordsUseCaseImpl(repo)
    }

    fun provideWordsListFactory(context: Context?): WordsListViewModelFactory {
        val useCase = provideGetWordsUseCase(context)
        return WordsListViewModelFactory(useCase)
    }

    companion object {
        @JvmStatic
        var instance: ServiceLocator? = null
            get() {
                if (field == null) {
                    synchronized(ServiceLocator::class.java) { field = ServiceLocator() }
                }
                return field
            }
            private set
    }
}