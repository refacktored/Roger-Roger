package com.refacktored.rogerroger.repositories

import com.refacktored.rogerroger.data.SearchResponse
import com.refacktored.rogerroger.network.MyApi
import io.reactivex.Single


class SearchRepository {

    fun userSearch(callsign: String): Single<SearchResponse> {

        //TODO - bad practice use injection koin?
        return MyApi().userSearch(callsign)
            .map {
                it
            }
    }
}