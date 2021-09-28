package com.example.instabugsearchwords.utils

import java.util.*
import java.util.concurrent.Executors

object Utils {

    val executorService = Executors.newFixedThreadPool(4)

    fun sortByValue(hm: Map<String, Int>, ascending: Boolean): HashMap<String, Int> {
        val list: List<Map.Entry<String, Int>> = LinkedList(hm.entries)

        Collections.sort(list) { o1, o2 ->
            if (ascending) o1.value.compareTo(o2.value) else o2.value.compareTo(
                o1.value
            )
        }


        val temp: HashMap<String, Int> = LinkedHashMap()
        for ((key, value) in list) {
            temp[key] = value
        }
        return temp
    }
}