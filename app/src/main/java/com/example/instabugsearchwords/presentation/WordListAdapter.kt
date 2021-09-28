package com.example.instabugsearchwords.presentation

import android.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.example.instabugsearchwords.presentation.WordListAdapter.WordsViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.instabugsearchwords.R
import android.widget.TextView
import java.util.ArrayList

class WordListAdapter : RecyclerView.Adapter<WordsViewHolder>() {
    private val wordsList: MutableList<Pair<String, Int>> = ArrayList()
    fun setData(newWords: List<Pair<String, Int>>?) {
        wordsList.clear()
        wordsList.addAll(newWords!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.word_list_item, parent, false)
        return WordsViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(wordsList[position].first, wordsList[position].second)
    }

    override fun getItemCount(): Int {
        return wordsList.size
    }

    inner class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mWordName: TextView = itemView.findViewById(R.id.tv_word_name)
        private val mWordCount: TextView = itemView.findViewById(R.id.tv_word_count)
        fun bind(name: String, count: Int) {
            mWordName.text = name
            mWordCount.text = count.toString()
        }

    }
}