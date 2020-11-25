package com.faircorp.model.apiService

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MyApiServices {
    val windowsApiService: WindowApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://faircorp-application.cleverapps.io/api/")
            .build()
            .create(WindowApiService::class.java)
    }
    val roomApiService: RoomApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://faircorp-application.cleverapps.io/api/")
            .build()
            .create(RoomApiService::class.java)
    }
    val heaterApiService: HeaterApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://faircorp-application.cleverapps.io/api/")
            .build()
            .create(HeaterApiService::class.java)
    }
}