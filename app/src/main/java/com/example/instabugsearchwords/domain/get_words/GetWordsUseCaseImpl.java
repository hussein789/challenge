package com.example.instabugsearchwords.domain.get_words;

import com.example.instabugsearchwords.data.repo.WordsCallback;
import com.example.instabugsearchwords.data.repo.WordsRepo;

public class GetWordsUseCaseImpl implements GetWordsUseCase {

    WordsRepo repo;

    public GetWordsUseCaseImpl(WordsRepo wordsRepo) {
        this.repo = wordsRepo;
    }

    @Override
    public void getWords(WordsCallback callback) {
        repo.getWords(callback);
    }

    @Override
    public void refreshWords(WordsCallback callback) {
         repo.refreshWords(callback);
    }
}
