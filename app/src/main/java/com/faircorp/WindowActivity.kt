package com.faircorp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.apiService.MyApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WindowActivity : BasicActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //...
        val param = intent.getStringExtra(WINDOW_NAME_PARAM)
        val windowName = findViewById<TextView>(R.id.txt_window_name)

        val id = intent.getLongExtra(WINDOW_ID, 0)
//        val window = windowService.findById(id)
        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { MyApiServices().windowsApiService.findById(id).execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        val window = it.body()

                        if (window != null) {
                            findViewById<TextView>(R.id.txt_preview_window_name).text = window.name
                            findViewById<TextView>(R.id.txt_room_name).text = window.room.name
                            findViewById<TextView>(R.id.txt_window_current_temperature).text =
                                window.room.currentTemperature?.toString()
                            findViewById<TextView>(R.id.txt_window_target_temperature).text =
                                window.room.targetTemperature?.toString()
                            findViewById<TextView>(R.id.txt_window_status).text =
                                window.status.toString()
                        }
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on window loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
        if (param != null)
            windowName.text = param
    }
}