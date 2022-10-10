package com.refacktored.rogerroger

import com.refacktored.rogerroger.data.SearchResponse

interface SearchListener {
    fun onStarted()
    fun onSuccess(searchResponse: SearchResponse)
    fun onFailure(message: String)
}