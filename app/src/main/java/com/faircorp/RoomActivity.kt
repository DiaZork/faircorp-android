package com.faircorp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.apiService.MyApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra(ROOM_ID, 0)

        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { MyApiServices().roomApiService.findById(id).execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        val room = it.body()

                        if (room != null) {
                            findViewById<TextView>(R.id.txt_view_room_name).text = room.name
                            findViewById<TextView>(R.id.txt_view_room_level).text = room.level.toString()
                            findViewById<TextView>(R.id.txt_view_room_type).text = room.type
                            findViewById<TextView>(R.id.txt_view_room_current_temp).text = if (room.currentTemperature != null) room.currentTemperature.toString() else ""
                            findViewById<TextView>(R.id.txt_view_room_target_temp).text = if (room.targetTemperature != null) room.targetTemperature.toString() else ""
                        }
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on room loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}