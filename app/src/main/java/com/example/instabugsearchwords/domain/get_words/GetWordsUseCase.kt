package com.example.instabugsearchwords.domain.get_words

import com.example.instabugsearchwords.data.repo.WordsCallback

interface GetWordsUseCase {
    fun getWords(callback: WordsCallback)
    fun refreshWords(callback: WordsCallback)
}