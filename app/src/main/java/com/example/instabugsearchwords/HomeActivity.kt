package com.example.instabugsearchwords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instabugsearchwords.databinding.ActivityHomeBinding
import com.example.instabugsearchwords.presentation.WordsListFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater);
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,WordsListFragment.newInstance())
            .commit()
    }
}