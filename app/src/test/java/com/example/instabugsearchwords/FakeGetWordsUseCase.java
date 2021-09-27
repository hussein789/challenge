package com.example.instabugsearchwords;

import com.example.instabugsearchwords.data.repo.WordsCallback;
import com.example.instabugsearchwords.domain.get_words.GetWordsUseCase;
import com.example.instabugsearchwords.domain.get_words.GetWordsUseCaseImpl;

public class FakeGetWordsUseCase implements GetWordsUseCase {
    @Override
    public void getWords(WordsCallback callback) {

    }

    @Override
    public void refreshWords(WordsCallback callback) {

    }
}
