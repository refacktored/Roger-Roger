package com.refacktored.rogerroger.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OtherInfoData(
    @Expose
    @SerializedName("grantDate")
    val grantDate: String? = null,
    @Expose
    @SerializedName("expiryDate")
    val expiryDate: String? = null,
    @Expose
    @SerializedName("lastActionDate")
    val lastActionDate: String? = null,
    @Expose
    @SerializedName("frn")
    val frn: String? = null,
    @Expose
    @SerializedName("ulsUrl")
    val ulsUrl: String? = null
)