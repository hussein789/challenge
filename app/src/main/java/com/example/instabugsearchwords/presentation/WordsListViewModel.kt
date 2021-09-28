package com.example.instabugsearchwords.presentation

import android.util.Pair
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instabugsearchwords.data.repo.WordsCallback
import com.example.instabugsearchwords.domain.get_words.GetWordsUseCase
import com.example.instabugsearchwords.utils.Utils
import java.util.*

class WordsListViewModel(private val wordsUseCase: GetWordsUseCase) : ViewModel() {

    private var wordsMap: MutableMap<String, Int> = HashMap()
    private var isSortAscending = true

    var showLoading = MutableLiveData<Boolean>()
    var showError = MutableLiveData<Boolean>()
    var showEmptyState = MutableLiveData<Boolean>()
    var updateList = MutableLiveData<List<Pair<String, Int>>>()
    var showSearchLD = MutableLiveData(false)

    fun init() {
        getWordsList()
    }

    private fun getWordsList() {

        showLoading.postValue(true)
        wordsUseCase.getWords(object : WordsCallback {
            override fun onSuccess(response: String) {
                if (response.isEmpty() == true) {
                    showEmptyState.postValue(true)
                } else {
                    updateWordsMapFromResponse(response)
                    val formattedList = getOrdinalListFromMap()
                    updateList(formattedList)
                }
                showLoading.postValue(false)
            }

            override fun onFail(message: String) {
                showError.postValue(true)
                showLoading.postValue(false)
            }
        })
    }

    private fun updateList(formattedList: List<Pair<String, Int>>) {
        updateList.postValue(formattedList)
    }

    private fun getOrdinalListFromMap(): List<Pair<String, Int>> {
            val list: MutableList<Pair<String, Int>> = ArrayList()
            for ((key, value) in wordsMap) {
                list.add(Pair(key, value))
            }
            return list
        }

    fun updateWordsMapFromResponse(data: String) {
        wordsMap.clear()
        val arr = data.split(" ".toRegex()).toTypedArray()
        for (i in arr.indices) {
            if (!arr[i].isEmpty() && isWord(arr[i])) {
                try {
                    var count = wordsMap[arr[i]]!!
                    wordsMap[arr[i]] = ++count
                } catch (exception: Exception) {
                    wordsMap[arr[i]] = 1
                }
            }
        }
    }

    private fun isWord(s: String): Boolean {
        if (s.length < 3) return false
        for (i in 0 until s.length) {
            if (!Character.isLetter(s[i])) return false
        }
        return true
    }

    fun onSearchClicked() {
        showSearchLD.postValue(!showSearchLD.value!!)
    }

    fun onSearchTextChanged(searchedText: String) {
        if (searchedText == null || searchedText.isEmpty()) {
            val list = getOrdinalListFromMap()
            updateList(list)
        } else {
            val searchedList = getSearchedListFromMap(searchedText)
            updateList(searchedList)
        }
    }

    private fun getSearchedListFromMap(searchedText: String): List<Pair<String, Int>> {
        val list: MutableList<Pair<String, Int>> = ArrayList()
        for ((key, value) in wordsMap) {
            if (key.toLowerCase().contains(searchedText.toLowerCase())) {
                list.add(Pair(key, value))
            }
        }
        return list
    }

    fun onSortClicked() {
        val sortedList = sortItems()
        updateList(sortedList)
        isSortAscending = !isSortAscending
    }

    fun sortItems(): List<Pair<String, Int>> {
        val sortedList = Utils.sortByValue(wordsMap, isSortAscending)
        val list: MutableList<Pair<String, Int>> = ArrayList()
        for ((key, value) in sortedList) {
            list.add(Pair(key,value))
        }
        return list
    }
}