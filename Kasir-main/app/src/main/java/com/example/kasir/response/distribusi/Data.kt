package com.example.kasir.response.distribusi


import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Expose
    @SerializedName("id_distribusi")
    val idDistribusi: Int,
    @Expose
    @SerializedName("nm_distribusi")
    val nmDistribusi: String,
    @Expose
    @SerializedName("ongkir")
    val ongkir: String,
    @Expose
    @SerializedName("service")
    val service: String,
    @Expose
    @SerializedName("tax")
    val tax: String
):Parcelable