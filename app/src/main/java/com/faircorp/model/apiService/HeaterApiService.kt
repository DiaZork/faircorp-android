package com.faircorp.model.apiService

import com.faircorp.model.dto.HeaterDto
import com.faircorp.model.dto.WindowDto
import retrofit2.Call
import retrofit2.http.*

interface HeaterApiService {
    @GET("heaters")
    fun findAll(): Call<List<HeaterDto>>

    @GET("heaters/{id}")
    fun findById(@Path("id") id: Long): Call<HeaterDto>

    @POST("heaters")
    fun create(@Body heater: HeaterDto): Call<HeaterDto>

    @DELETE("heaters/{id}")
    fun delete(@Path("id") id: Long)

}