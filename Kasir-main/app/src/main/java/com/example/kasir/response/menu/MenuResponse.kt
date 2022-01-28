package com.example.kasir.response.menu


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MenuResponse(
    @Expose
    @SerializedName("data")
    val `data`: List<Data>,
    @Expose
    @SerializedName("meta")
    val meta: Meta
)