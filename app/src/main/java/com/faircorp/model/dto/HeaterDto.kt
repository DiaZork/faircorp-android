package com.faircorp.model.dto

enum class HeaterStatus {
    ON, OFF
}

data class HeaterDto(
    val id: Long,
    val name: String,
    val power: Long?,
    val room: RoomDto,
    val heaterStatus: HeaterStatus
)
