package com.faircorp.model.apiService

import com.faircorp.model.dto.RoomDto
import retrofit2.Call
import retrofit2.http.*

interface RoomApiService {
    @GET("rooms")
    fun findAll(): Call<List<RoomDto>>


    @GET("rooms/{id}")
    fun findById(@Path("id") id: Long): Call<RoomDto>


    @POST("rooms")
    fun create(@Body room: RoomDto): Call<RoomDto>


    @DELETE("rooms/{id}")
    fun delete(@Path("id") id: Long)

    @PUT("rooms/{id}/switchWindows")
    fun switchWindows(@Path("id") id: Long)

    @PUT("rooms/{id}/switchHeaters")
    fun switchHeaters(@Path("id") id: Long)
}