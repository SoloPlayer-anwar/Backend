package com.example.kasir.room

import androidx.room.Entity
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class CartDoa(
    var id_lokasi: Int,

)