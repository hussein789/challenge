package com.example.instabugsearchwords;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsViewHolder> {

    private List<Pair<String,Integer>> wordsList = new ArrayList<>();

    void setData(List<Pair<String,Integer>> newWords){
        wordsList.clear();
        wordsList.addAll(newWords);

    }

    @NonNull
    @Override
    public WordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.word_list_item,parent,false);
        return new WordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordsViewHolder holder, int position) {
        holder.bind(wordsList.get(position).first,wordsList.get(position).second);
    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }

    class WordsViewHolder extends RecyclerView.ViewHolder {
        private TextView mWordName;
        private TextView mWordCount;

        public WordsViewHolder(@NonNull View itemView) {
            super(itemView);
            mWordName = itemView.findViewById(R.id.tv_word_name);
            mWordCount = itemView.findViewById(R.id.tv_word_count);
        }

        private void bind(String name,int count){
            mWordName.setText(name);
            mWordCount.setText(String.valueOf(count));
        }
    }
}
