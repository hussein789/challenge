package com.example.instabugsearchwords;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TestViewModel  extends AndroidViewModel {

    MutableLiveData<String> words = new MutableLiveData<>();

    public TestViewModel(@NonNull Application application) {
        super(application);
    }
}
