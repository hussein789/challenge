package com.example.instabugsearchwords.presentation;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.instabugsearchwords.R;
import com.example.instabugsearchwords.WordsAdapter;
import com.example.instabugsearchwords.databinding.WordsListFragmentBinding;
import com.example.instabugsearchwords.domain.di.ServiceLocator;

import java.util.List;

public class WordsListFragment extends Fragment {

    private WordsListViewModel mViewModel;
    private WordsListFragmentBinding binding;
    private WordListAdapter adapter;

    public static WordsListFragment newInstance() {
        return new WordsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = WordsListFragmentBinding.inflate(inflater, container, false);
        WordsListViewModelFactory factory = ServiceLocator.getInstance().provideWordsListFactory(requireActivity());
        mViewModel = new ViewModelProvider(this, factory).get(WordsListViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initTextWatcher();
        observeViewModel();
        mViewModel.getWords();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedItem = item.getItemId();
        if (selectedItem == R.id.action_search) {
            mViewModel.onSearchClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        binding.rvWords.setLayoutManager(linearLayoutManager);
        binding.rvWords.setHasFixedSize(true);
        adapter = new WordListAdapter();
        binding.rvWords.setAdapter(adapter);
    }

    private void initTextWatcher() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.onSearchTextChanged(s.toString());
            }
        });
    }

        private void observeViewModel () {
            mViewModel.showLoading.observe(getViewLifecycleOwner(), showloading -> {
                handleLoading(showloading);
            });

            mViewModel.showError.observe(getViewLifecycleOwner(), showError -> {
                handleErrorState(showError);
            });

            mViewModel.showEmptyState.observe(getViewLifecycleOwner(), showEmpty -> {
                handleEmptyState(showEmpty);
            });

            mViewModel.updateList.observe(getViewLifecycleOwner(), list -> {
                updateList(list);
            });

            mViewModel.showSearchLD.observe(getViewLifecycleOwner(), show -> {
                handleSearch(show);
            });

        }

        private void handleSearch (Boolean show){
            if (show) binding.etSearch.setVisibility(View.VISIBLE);
            else binding.etSearch.setVisibility(View.GONE);
        }

        private void updateList (List < Pair < String, Integer >> list){
            adapter.setData(list);
            adapter.notifyDataSetChanged();
        }

        private void handleEmptyState (Boolean showEmpty){
            if (showEmpty) {
                binding.tvError.setVisibility(View.GONE);
                binding.tvEmptyText.setVisibility(View.VISIBLE);
                binding.rvWords.setVisibility(View.GONE);
            }
        }

        private void handleErrorState (Boolean showError){
            if (showError) {
                binding.tvError.setVisibility(View.VISIBLE);
                binding.tvEmptyText.setVisibility(View.GONE);
                binding.rvWords.setVisibility(View.GONE);
            }
        }

        private void handleLoading (Boolean showloading){
            if (showloading) binding.pbLoadingIndicator.setVisibility(View.VISIBLE);
            else binding.pbLoadingIndicator.setVisibility(View.GONE);
        }
    }