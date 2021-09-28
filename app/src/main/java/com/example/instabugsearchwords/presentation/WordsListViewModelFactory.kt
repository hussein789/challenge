package com.example.instabugsearchwords.presentation

import com.example.instabugsearchwords.domain.get_words.GetWordsUseCase
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.instabugsearchwords.presentation.WordsListViewModel
import java.lang.IllegalArgumentException

class WordsListViewModelFactory(var getWordsUseCase: GetWordsUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordsListViewModel::class.java)) {
            return WordsListViewModel(getWordsUseCase) as T
        }
        throw IllegalArgumentException("Unknown view model found")
    }
}