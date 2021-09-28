package com.example.instabugsearchwords

import com.example.instabugsearchwords.presentation.WordsListFragment.Companion.newInstance
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instabugsearchwords.R
import com.example.instabugsearchwords.presentation.WordsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}