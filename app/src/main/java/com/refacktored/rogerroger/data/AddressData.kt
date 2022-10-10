package com.refacktored.rogerroger.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddressData(
    @Expose
    @SerializedName("line1")
    val line1: String? = null,
    @Expose
    @SerializedName("line2")
    val line2: String? = null,
    @Expose
    @SerializedName("attn")
    val attn: String? = null
)