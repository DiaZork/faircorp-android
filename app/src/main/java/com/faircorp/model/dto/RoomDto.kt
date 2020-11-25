
package com.faircorp.model.dto

data class RoomDto(
    val id: Long,
    val level: Int,
    val name: String,
    val type: String?,
    val currentTemperature: Double?,
    val targetTemperature: Double?
)