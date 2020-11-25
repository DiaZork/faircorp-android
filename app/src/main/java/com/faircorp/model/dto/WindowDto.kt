package com.faircorp.model.dto

enum class WindowStatus {
    OPEN, CLOSED
}

data class WindowDto(
    val id: Long,
    val name: String,
    val room: RoomDto,
    val status: WindowStatus
)