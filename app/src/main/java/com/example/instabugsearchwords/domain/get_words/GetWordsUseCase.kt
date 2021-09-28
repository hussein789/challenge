package com.example.instabugsearchwords.domain.get_words;

import com.example.instabugsearchwords.data.repo.WordsCallback;
import com.example.instabugsearchwords.data.repo.WordsRepo;

public interface GetWordsUseCase{
    void getWords(WordsCallback callback);
    void refreshWords(WordsCallback callback);
}

