package com.example.instabugsearchwords.presentation;

import android.util.Pair;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.instabugsearchwords.data.repo.WordsCallback;
import com.example.instabugsearchwords.domain.get_words.GetWordsUseCase;
import com.example.instabugsearchwords.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsListViewModel extends ViewModel {

    private GetWordsUseCase wordsUseCase;

    public WordsListViewModel(GetWordsUseCase wordsUseCase) {
        this.wordsUseCase = wordsUseCase;
    }

    Map<String, Integer> wordsMap = new HashMap<>();
    boolean isSortAscending = true;

    MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    MutableLiveData<Boolean> showError = new MutableLiveData<>();
    MutableLiveData<Boolean> showEmptyState = new MutableLiveData<>();
    MutableLiveData<List<Pair<String, Integer>>> updateList = new MutableLiveData<>();
    MutableLiveData<Boolean> showSearchLD = new MutableLiveData<>(false);

    public void init() {
       getWords();
    }

    void getWords() {
        showLoading.postValue(true);
        wordsUseCase.getWords(new WordsCallback() {
            @Override
            public void onSuccess(String response) {
                if(response.isEmpty()){
                    showEmptyState.postValue(true);
                } else {
                    updateWordsMapFromResponse(response);
                    List<Pair<String, Integer>> formattedList = getOrdinalListFromMap();
                    updateList(formattedList);
                }

                showLoading.postValue(false);
            }

            @Override
            public void onFail(String message) {
                showError.postValue(true);
                showLoading.postValue(false);
            }
        });
    }

    private void updateList(List<Pair<String, Integer>> formattedList) {
        updateList.postValue(formattedList);
    }

    private List<Pair<String, Integer>> getOrdinalListFromMap() {
        List<Pair<String, Integer>> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            list.add(new Pair(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    public void updateWordsMapFromResponse(String data) {
        wordsMap.clear();
        String[] arr = data.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].isEmpty() && isWord(arr[i])) {
                try {
                    int count = wordsMap.get(arr[i]);
                    wordsMap.put(arr[i], ++count);
                } catch (Exception exception) {
                    wordsMap.put(arr[i], 1);
                }
            }
        }
    }

    private boolean isWord(String s) {
        if(s.length() < 3) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i)))
                return false;
        }
        return true;
    }

    public void onSearchClicked() {
        showSearchLD.postValue(!showSearchLD.getValue());
    }

    public void onSearchTextChanged(String searchedText) {
        if (searchedText == null || searchedText.isEmpty()) {
            List<Pair<String, Integer>> list = getOrdinalListFromMap();
            updateList(list);
        } else {
            List<Pair<String, Integer>> searchedList = getSearchedListFromMap(searchedText);
            updateList(searchedList);
        }
    }

    private List<Pair<String, Integer>> getSearchedListFromMap(String searchedText) {
        List<Pair<String, Integer>> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            if (entry.getKey().toLowerCase().contains(searchedText.toLowerCase())) {
                list.add(new Pair(entry.getKey(), entry.getValue()));
            }
        }
        return list;
    }

    public void onSortClicked() {
        List<Pair<String,Integer>> sortedList = sortItems();
        updateList(sortedList);
        isSortAscending = !isSortAscending;
    }

    public List<Pair<String,Integer>> sortItems(){
        HashMap<String,Integer> sortedList = Utils.sortByValue(wordsMap,isSortAscending);
        List<Pair<String,Integer>> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedList.entrySet()) {
            Pair<String,Integer> newItem = new Pair(entry.getKey(),entry.getValue());
            list.add(newItem);
        }
        return list;
    }

}