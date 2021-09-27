package com.example.instabugsearchwords;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.instabugsearchwords.data.network.NetworkUtils;
import com.example.instabugsearchwords.presentation.WordsListFragment;
import com.example.instabugsearchwords.utils.Utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView mWordsRecyclerView;
//    private TextView errorTextView;
//    private TextView emptyTextView;
//    private ProgressBar loadingIndicator;
//    private EditText searchEditText;
//
//    private static final int WORDS_LOADER_ID = 22;
//
//    private static final String SEARCH_URL = "SEARCH_URL";
//    private static final String SORTED_RESULT_KEY = "SORTED_RESULT_KEY";
//    private static final String IS_LIST_SORTED = "IS_LIST_SORTED";
//    private static final String TOGGLE_SORT_KEY = "TOGGLE_SORT_KEY";

//    private WordsAdapter adapter;

//    Map<String, Integer> wordsMap = new HashMap<>();
//    private Boolean ascending = true;
//    private boolean isSorted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WordsListFragment fragment = WordsListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();

//        if(savedInstanceState != null){
//            if(savedInstanceState.containsKey(SORTED_RESULT_KEY)){
//                ascending = savedInstanceState.getBoolean(SORTED_RESULT_KEY);
//                isSorted = savedInstanceState.getBoolean(IS_LIST_SORTED);
//            }
//        }
//
//        initViews();
//        initRecyclerView();
//        initTextWatcher();
//        initLoader();
    }

//    private void initLoader() {
//        getSupportLoaderManager().initLoader(WORDS_LOADER_ID,null,this);
//    }
//
//    private void initRecyclerView() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        mWordsRecyclerView.setLayoutManager(linearLayoutManager);
//        mWordsRecyclerView.setHasFixedSize(true);
//        adapter = new WordsAdapter();
//        mWordsRecyclerView.setAdapter(adapter);
//    }
//
//    private void initViews() {
//        mWordsRecyclerView = findViewById(R.id.rv_words);
//        errorTextView = findViewById(R.id.tv_error);
//        emptyTextView = findViewById(R.id.tv_empty_text);
//        loadingIndicator = findViewById(R.id.pb_loading_indicator);
//        searchEditText = findViewById(R.id.et_search);
//    }
//
//    private void initTextWatcher() {
//        searchEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String newText = s.toString();
//                emptyWordsList();
//                if (newText.isEmpty()) {
//                   updateList();
//                } else {
//                    List<Pair<String,Integer>> list = new ArrayList<>();
//                    for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
//                        if (entry.getKey().toLowerCase().contains(newText.toLowerCase())) {
//                            list.add(new Pair(entry.getKey(),entry.getValue()));
//                        }
//                    }
//                    updateWordsList(list);
//                }
//            }
//        });
//    }
//
//    private void updateWordsList(List<Pair<String,Integer>> newList){
//        adapter.setData(newList);
//        adapter.notifyDataSetChanged();
//    }
//
//    private void emptyWordsList() {
//        adapter.setData(new ArrayList<>());
//        adapter.notifyDataSetChanged();
//    }
//
//    private void loadWords() {
//        showResults();
//        URL searchURL = NetworkUtils.buildUrl();
//
//        Bundle bundle =  new Bundle();
//        bundle.putString(SEARCH_URL,searchURL.toString());
//        LoaderManager loaderManager = getSupportLoaderManager();
//        Loader wordsLoader = loaderManager.getLoader(WORDS_LOADER_ID);
//        if(wordsLoader == null){
//            loaderManager.initLoader(WORDS_LOADER_ID,bundle,this);
//        } else {
//            loaderManager.restartLoader(WORDS_LOADER_ID,bundle,this);
//        }
//    }
//
//    private void showResults() {
//        mWordsRecyclerView.setVisibility(View.VISIBLE);
//        errorTextView.setVisibility(View.GONE);
//        emptyTextView.setVisibility(View.GONE);
//    }
//
//    private void showErrorText() {
//        mWordsRecyclerView.setVisibility(View.GONE);
//        errorTextView.setVisibility(View.VISIBLE);
//        emptyTextView.setVisibility(View.GONE);
//    }
//
//    private void showEmptyText() {
//        mWordsRecyclerView.setVisibility(View.GONE);
//        errorTextView.setVisibility(View.GONE);
//        emptyTextView.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int selectedItem = item.getItemId();
//        if (selectedItem == R.id.action_search) {
//            if (searchEditText.getVisibility() == View.VISIBLE) {
//                searchEditText.setVisibility(View.GONE);
//            } else {
//                searchEditText.setVisibility(View.VISIBLE);
//            }
//        } else if(selectedItem == R.id.action_sort_ascending){
//            isSorted = true;
//            ascending = true;
//            emptyWordsList();
//            updateList();
//        } else if (selectedItem == R.id.action_sort_descending){
//            isSorted = true;
//            ascending = false;
//            emptyWordsList();
//            updateList();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void updateList() {
//        if(isSorted){
//            HashMap<String,Integer> sortedList = Utils.sortByValue(wordsMap,ascending);
//            List<Pair<String,Integer>> list = new ArrayList<>();
//            for (Map.Entry<String, Integer> entry : sortedList.entrySet()) {
//                list.add(new Pair(entry.getKey(),entry.getValue()));
//            }
//            updateWordsList(list);
//        } else {
//            List<Pair<String,Integer>> list = new ArrayList<>();
//            for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
//                list.add(new Pair(entry.getKey(),entry.getValue()));
//            }
//            updateWordsList(list);
//        }
//    }
//
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putBoolean(SORTED_RESULT_KEY,ascending);
//        outState.putBoolean(IS_LIST_SORTED,isSorted);
//    }
//
//    @NonNull
//    @Override
//    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
//        return new AsyncTaskLoader<String>(this) {
//            String cachedWordsResponse;
//
//            @Override
//            protected void onStartLoading() {
//                if (cachedWordsResponse!=null && !cachedWordsResponse.isEmpty()){
//                    deliverResult(cachedWordsResponse);
//                } else {
//                    loadingIndicator.setVisibility(View.VISIBLE);
//                    forceLoad();
//                }
//            }
//
//            @Nullable
//            @Override
//            public String loadInBackground() {
//                try {
//                    URL searchURL = NetworkUtils.buildUrl();
//                    return NetworkUtils.getResponseFromHttpUrl(searchURL);
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                    return null;
//                }
//            }
//
//            @Override
//            public void deliverResult(@Nullable String data) {
//                cachedWordsResponse = data;
//                super.deliverResult(data);
//            }
//        };
//    }
//
//    @Override
//    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
//        loadingIndicator.setVisibility(View.GONE);
//        if (data == null) {
//            showErrorText();
//        } else if (data.isEmpty()) {
//            showEmptyText();
//        } else {
//            wordsMap.clear();
//            String[] arr = data.split(" ");
//            for (int i = 0; i < arr.length; i++) {
//                if (!arr[i].isEmpty() && isWord(arr[i])) {
//                    try {
//                        int count = wordsMap.get(arr[i]);
//                        wordsMap.put(arr[i], ++count);
//                    } catch (Exception exception) {
//                        wordsMap.put(arr[i], 1);
//                    }
//
//                }
//            }
//            updateList();
//        }
//    }
//
//
//    @Override
//    public void onLoaderReset(@NonNull Loader<String> loader) {
//
//    }
//
//    private boolean isWord(String s) {
//        for (int i = 0; i < s.length(); i++) {
//            if (!Character.isLetter(s.charAt(i)))
//                return false;
//        }
//        return true;
//    }

}