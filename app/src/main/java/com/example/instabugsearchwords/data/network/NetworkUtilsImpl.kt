package com.example.instabugsearchwords.data.network

import android.net.Uri
import com.example.instabugsearchwords.data.network.NetworkUtils
import com.example.instabugsearchwords.data.network.NetworkUtilsImpl
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.Throws

class NetworkUtilsImpl : NetworkUtils {
    override fun buildUrl(): URL? {
        val uri = Uri.parse(INSTABUG_SEARCH_URL).buildUpon().build()
        return try {
            URL(uri.toString())
        } catch (exception: Exception) {
            null
        }
    }

    @Throws(IOException::class)
    override fun getResponseFromHttpUrl(url: URL?): String? {
        val urlConnection = url!!.openConnection() as HttpURLConnection
        return try {
            val `in` = urlConnection.inputStream
            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")
            val hasInput = scanner.hasNext()
            if (hasInput) {
                scanner.next()
            } else {
                null
            }
        } finally {
            urlConnection.disconnect()
        }
    }

    companion object {
        private const val INSTABUG_SEARCH_URL = "https://instabug.com"
    }
}