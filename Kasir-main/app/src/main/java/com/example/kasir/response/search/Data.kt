package com.example.kasir.response.search


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Data(
    @Expose
    @SerializedName("harga")
    val harga: Int?,

    @Expose
    @SerializedName("id_distribusi")
    val idDistribusi: Int?,

    @Expose
    @SerializedName("id_harga")
    val idHarga: Int?,

    @Expose
    @SerializedName("id_menu")
    val idMenu: Int?,

    @Expose
    @SerializedName("image")
    val image: String?,

    @Expose
    @SerializedName("nm_distribusi")
    val nmDistribusi: String?,

    @Expose
    @SerializedName("nm_menu")
    val nmMenu: String?
):Parcelable