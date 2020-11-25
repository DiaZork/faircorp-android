package com.faircorp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.apiService.MyApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeaterActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra(HEATER_ID, 0)

        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { MyApiServices().heaterApiService.findById(id).execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        val heater = it.body()

                        if (heater != null) {
                            findViewById<TextView>(R.id.txt_view_heater_name).text = heater.name
                            findViewById<TextView>(R.id.txt_view_heater_power).text =
                                heater.power.toString()
                            findViewById<TextView>(R.id.txt_view_heater_status).text =
                                heater.heaterStatus.toString()
                            findViewById<TextView>(R.id.txt_view_heater_room_name).text =
                                heater.room.name
                        }
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on heater loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}