package com.example.kasir.response.category


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @Expose
    @SerializedName("kategori")
    val kategori: String,
    @Expose
    @SerializedName("kd_kategori")
    val kdKategori: Int,
    @Expose
    @SerializedName("lokasi")
    val lokasi: String
)