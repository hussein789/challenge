package com.example.instabugsearchwords.domain.di;

import android.content.Context;

import com.example.instabugsearchwords.data.db.WordsCache;
import com.example.instabugsearchwords.data.db.WordsCacheImpl;
import com.example.instabugsearchwords.data.network.NetworkUtils;
import com.example.instabugsearchwords.data.network.NetworkUtilsImpl;
import com.example.instabugsearchwords.data.repo.WordsRepo;
import com.example.instabugsearchwords.data.repo.WordsRepoImpl;
import com.example.instabugsearchwords.domain.get_words.GetWordsUseCase;
import com.example.instabugsearchwords.domain.get_words.GetWordsUseCaseImpl;
import com.example.instabugsearchwords.presentation.WordsListViewModelFactory;

public class ServiceLocator {

    private static ServiceLocator instance = null;

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    public WordsCache provideWordsCache(Context context){
        return new WordsCacheImpl(context);
    }

    public NetworkUtils provideNetworkUtils(){
        return new NetworkUtilsImpl();
    }

    public WordsRepo provideWordsRepo(Context context){
        NetworkUtils networkUtils = provideNetworkUtils();
        WordsCache wordsCache = provideWordsCache(context);
        return new WordsRepoImpl(networkUtils,wordsCache);
    }

    public GetWordsUseCase provideGetWordsUseCase(Context context){
        WordsRepo repo = provideWordsRepo(context);
        return new GetWordsUseCaseImpl(repo);
    }

    public WordsListViewModelFactory provideWordsListFactory(Context context){
        GetWordsUseCase useCase = provideGetWordsUseCase(context);
        return new WordsListViewModelFactory(useCase);
    }
}
