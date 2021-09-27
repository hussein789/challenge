package com.example.instabugsearchwords.presentation;

import android.util.Pair;

import com.example.instabugsearchwords.FakeGetWordsUseCase;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsListViewModelTest extends TestCase {

    FakeGetWordsUseCase fakeUseCase = new FakeGetWordsUseCase();
    WordsListViewModel viewModel = new WordsListViewModel(fakeUseCase);

    @Test
    public void sortItems_sortAscending_returnAscendingItems(){
        prepareMap();

        List<Pair<String,Integer>> list = viewModel.sortItems(true);

        assertEquals(list.get(0),new Pair("Talk",1));
        assertEquals(list.get(1),new Pair("performance",5));
        assertEquals(list.get(2),new Pair("Insta",10));
    }

    @Test
    public void sortItems_sortDescending_returnAscendingItems(){
        prepareMap();

        List<Pair<String,Integer>> list = viewModel.sortItems(true);

        assertEquals(list.get(2),new Pair("Talk",1));
        assertEquals(list.get(1),new Pair("performance",5));
        assertEquals(list.get(0),new Pair("Insta",10));
    }


    private void prepareMap() {
        Map<String,Integer> map = new HashMap<>();
        map.put("Talk",1);
        map.put("Insta",10);
        map.put("performance",5);

        viewModel.wordsMap.clear();
        viewModel.wordsMap = map;
    }
}