package com.example.instabugsearchwords.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.instabugsearchwords.domain.get_words.GetWordsUseCase;

public class WordsListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    GetWordsUseCase getWordsUseCase;

    public WordsListViewModelFactory(GetWordsUseCase getWordsUseCase) {
        this.getWordsUseCase = getWordsUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(WordsListViewModel.class)){
            return (T) new WordsListViewModel(getWordsUseCase);
        }
        throw new IllegalArgumentException("Unknown view model found");
    }
}
