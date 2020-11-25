package com.faircorp

import android.os.Bundle

const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"
const val WINDOW_ID = "com.faircorp.window.id"
const val ROOM_ID = "com.faircorp.room.id"
const val HEATER_ID = "com.faircorp.heater.id"

class MainActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
