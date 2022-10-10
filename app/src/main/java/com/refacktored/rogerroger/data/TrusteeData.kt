package com.refacktored.rogerroger.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrusteeData(
    @Expose
    @SerializedName("callsign")
    val callsign: String? = null,
    @Expose
    @SerializedName("name")
    val name: String? = null
)