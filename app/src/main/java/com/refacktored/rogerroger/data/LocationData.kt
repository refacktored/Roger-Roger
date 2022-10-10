package com.refacktored.rogerroger.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LocationData(
    @Expose
    @SerializedName("latitude")
    val latitude: String? = null,
    @Expose
    @SerializedName("longitude")
    val longitude: String? = null,
    @Expose
    @SerializedName("gridsquare")
    val gridsquare: String? = null
)