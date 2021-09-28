package com.example.instabugsearchwords.data.network;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtilsImpl implements NetworkUtils {

    private static String INSTABUG_SEARCH_URL = "https://instabug.com";

    @Override
    public URL buildUrl() {
        Uri uri = Uri.parse(INSTABUG_SEARCH_URL).buildUpon().build();
        try {
            URL url = new URL(uri.toString());
            return url;
        } catch (Exception exception) {
            return null;
        }
    }


    @Override
    public String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
