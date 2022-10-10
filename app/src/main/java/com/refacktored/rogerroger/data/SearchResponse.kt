package com.refacktored.rogerroger.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @Expose
    @SerializedName("status")
    val status: String? = null,
    @Expose
    @SerializedName("type")
    val type: String? = null,
    @Expose
    @SerializedName("current")
    val current: CurrentData? = null,
    @Expose
    @SerializedName("previous")
    val previous: PreviousData? = null,
    @Expose
    @SerializedName("trustee")
    val trustee: TrusteeData? = null,
    @Expose
    @SerializedName("name")
    val name: String? = null,
    @Expose
    @SerializedName("address")
    val address: AddressData? = null,
    @Expose
    @SerializedName("location")
    val location: LocationData? = null,
    @Expose
    @SerializedName("otherInfo")
    val otherInfo: OtherInfoData? = null
)