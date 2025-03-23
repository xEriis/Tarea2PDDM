package com.erynlexa.tarea2

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject
import java.net.URL
import kotlin.math.ceil

class PantallaDos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_dos)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val solicitaClima : Button = findViewById(R.id.actualizaClima)
        solicitaClima.setOnClickListener {
            Toast.makeText(this, "Actualizando...", Toast.LENGTH_SHORT).show()
            obtenClima()
        }
    }

    private fun obtenClima(){
        obtenClimaCiudad().execute()
    }

    inner class obtenClimaCiudad : AsyncTask<String, Void, String>(){

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?lat=19.3215473&lon=-99.1849188&appid=f6adade6884dd6823a33809867ec6cb9&units=metric&lang=es")
                    .readText(Charsets.UTF_8)
            }catch (e : Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val objetoJSON = JSONObject(result)
                val main = objetoJSON.getJSONObject("main")
                val clima = objetoJSON.getJSONArray("weather").getJSONObject(0)

                val descripcion = clima.getString("description")
                val temperaturaString = main.getString("temp")
                val temperaturaNum = temperaturaString.toFloat()
                val temperatura = ceil(temperaturaNum).toInt()
                val temperaturaRedondeada = temperatura.toString()
                val temperaturaActual = temperaturaRedondeada + "°C"

                findViewById<TextView>(R.id.temperaturaClima).text = temperaturaActual
                findViewById<TextView>(R.id.descripcionclima).text = descripcion

            }catch (e: Exception){
                findViewById<TextView>(R.id.temperaturaClima).text = null
                findViewById<TextView>(R.id.descripcionclima).text = null
            }
        }
    }
}