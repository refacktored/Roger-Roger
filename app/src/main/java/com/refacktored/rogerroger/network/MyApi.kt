package com.refacktored.rogerroger.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.refacktored.rogerroger.data.SearchResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {

    @GET("{callsign}/json")
    fun userSearch(@Path("callsign") callsign: String): Single<SearchResponse>

    companion object {
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .baseUrl("https://callook.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}
