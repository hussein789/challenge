package com.example.instabugsearchwords.domain.get_words

import com.example.instabugsearchwords.data.repo.WordsCallback
import com.example.instabugsearchwords.data.repo.WordsRepo

class GetWordsUseCaseImpl(var repo: WordsRepo) : GetWordsUseCase {
    override fun getWords(callback: WordsCallback) {
        repo.getWords(callback)
    }

    override fun refreshWords(callback: WordsCallback) {
        repo.refreshWords(callback)
    }
}