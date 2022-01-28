package com.example.kasir.network

import com.example.kasir.response.category.CategoryResponse
import com.example.kasir.response.distribusi.DistribusiResponse
import com.example.kasir.response.menu.MenuResponse
import com.example.kasir.response.search.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("get-kategori/takemori")
    fun category():Observable<CategoryResponse>

    @GET("get-distribusi")
    fun distribusi():Observable<DistribusiResponse>

    @GET("get-menu")
    fun menu(@Query("id_lokasi") id_lokasi: Int,
             @Query("id_dis") id_dis: Int,
             @Query("page") page:Int):Observable<MenuResponse>

    @GET("get-menu")
    fun search(@Query("id_lokasi") idLocation: Int,
               @Query("id_dis")idDis: Int):Observable<SearchResponse>
}