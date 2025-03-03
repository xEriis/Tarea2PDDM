package com.erynlexa.tarea2

import android.os.Bundle
import android.content.Intent;
import android.view.View;
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonVistaUno : Button = findViewById(R.id.botonV1)
        botonVistaUno.setOnClickListener {
            val aperturaVistaUno = Intent(this, PantallaUno::class.java)
            startActivity(aperturaVistaUno)
        }

        val botonVistaDos : Button = findViewById(R.id.botonV2)
        botonVistaDos.setOnClickListener {
            val aperturaVistaDos = Intent(this, PantallaDos::class.java)
            startActivity(aperturaVistaDos)
        }
    }

}