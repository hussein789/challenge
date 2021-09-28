package com.example.instabugsearchwords.data.network

import java.io.IOException
import java.net.URL
import kotlin.Throws

interface NetworkUtils {
    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL?): String?
    fun buildUrl(): URL?
}