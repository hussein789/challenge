package com.example.instabugsearchwords.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Pair
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instabugsearchwords.R
import com.example.instabugsearchwords.databinding.WordsListFragmentBinding
import com.example.instabugsearchwords.domain.di.ServiceLocator.Companion.instance

class WordsListFragment : Fragment() {
    private lateinit var mViewModel: WordsListViewModel
    private lateinit var binding: WordsListFragmentBinding
    private lateinit var adapter: WordListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WordsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = instance!!.provideWordsListFactory(requireActivity())
        mViewModel = ViewModelProvider(this, factory).get(WordsListViewModel::class.java)
        initRecyclerView()
        initTextWatcher()
        observeViewModel()
        mViewModel.init()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val selectedItem = item.itemId
        if (selectedItem == R.id.action_search) {
            mViewModel.onSearchClicked()
            return true
        } else if (selectedItem == R.id.action_sort) {
            mViewModel.onSortClicked()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        binding.rvWords.layoutManager = linearLayoutManager
        binding.rvWords.setHasFixedSize(true)
        adapter = WordListAdapter()
        binding.rvWords.adapter = adapter
    }

    private fun initTextWatcher() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                mViewModel.onSearchTextChanged(s.toString())
            }
        })
    }

    private fun observeViewModel() {
        mViewModel.showLoading.observe(
            viewLifecycleOwner,
            { showloading: Boolean -> handleLoading(showloading) })
        mViewModel.showError.observe(
            viewLifecycleOwner,
            { showError: Boolean -> handleErrorState(showError) })
        mViewModel.showEmptyState.observe(
            viewLifecycleOwner,
            { showEmpty: Boolean -> handleEmptyState(showEmpty) })
        mViewModel.updateList.observe(
            viewLifecycleOwner,
            { list -> updateList(list) })
        mViewModel.showSearchLD.observe(
            viewLifecycleOwner,
            { show: Boolean -> handleSearch(show) })
    }

    private fun handleSearch(show: Boolean) {
        if (show) binding.etSearch.visibility = View.VISIBLE else binding.etSearch.visibility =
            View.GONE
    }

    private fun updateList(list: List<Pair<String, Int>>) {
        showList()
        adapter.setData(list)
        adapter.notifyDataSetChanged()
    }

    private fun showList() {
        binding.tvError.visibility = View.GONE
        binding.tvEmptyText.visibility = View.GONE
        binding.rvWords.visibility = View.VISIBLE
    }

    private fun handleEmptyState(showEmpty: Boolean) {
        if (showEmpty) {
            binding.tvError.visibility = View.GONE
            binding.tvEmptyText.visibility = View.VISIBLE
            binding.rvWords.visibility = View.GONE
        }
    }

    private fun handleErrorState(showError: Boolean) {
        if (showError) {
            binding.tvError.visibility = View.VISIBLE
            binding.tvEmptyText.visibility = View.GONE
            binding.rvWords.visibility = View.GONE
        }
    }

    private fun handleLoading(showloading: Boolean) {
        if (showloading) binding.pbLoadingIndicator.visibility =
            View.VISIBLE else binding.pbLoadingIndicator.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance(): WordsListFragment {
            return WordsListFragment()
        }
    }
}