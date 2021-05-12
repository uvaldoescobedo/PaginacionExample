package com.example.paginacionexample.pojos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainData(
    @SerializedName("id") val id : Int,
    @SerializedName("author") val author : String,
    @SerializedName("width") val width : Int,
    @SerializedName("height") val height : Int,
    @SerializedName("url") val url : String,
    @SerializedName("download_url") val download_url : String
) : Parcelable