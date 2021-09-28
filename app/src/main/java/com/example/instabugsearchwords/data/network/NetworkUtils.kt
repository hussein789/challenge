package com.example.instabugsearchwords.data.network;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public interface NetworkUtils {
    String getResponseFromHttpUrl(URL url) throws IOException;
    URL buildUrl();
}

