package com.refacktored.rogerroger.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PreviousData(
    @Expose
    @SerializedName("callsign")
    val callsign: String? = null,
    @Expose
    @SerializedName("operClass")
    val operClass: String? = null
)