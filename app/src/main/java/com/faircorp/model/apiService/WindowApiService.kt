package com.faircorp.model.apiService

import com.faircorp.model.dto.WindowDto
import retrofit2.Call
import retrofit2.http.*

interface WindowApiService {
    @GET("windows")
    fun findAll(): Call<List<WindowDto>>

    @GET("windows/{id}")
    fun findById(@Path("id") id: Long): Call<WindowDto>

    @PUT("windows/{id}/switch")
    fun switch(@Path("id") id: Long): Call<WindowDto>

    @POST("windows")
    fun create(@Body window: WindowDto): Call<WindowDto>

    @DELETE("windows/{id}")
    fun delete(@Path("id") id: Long)
}