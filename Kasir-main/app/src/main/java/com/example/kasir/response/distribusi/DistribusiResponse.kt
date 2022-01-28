package com.example.kasir.response.distribusi


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DistribusiResponse(
    @Expose
    @SerializedName("data")
    val `data`: List<Data>,
    @Expose
    @SerializedName("meta")
    val meta: Meta
)