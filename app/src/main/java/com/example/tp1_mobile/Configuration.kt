package com.example.tp1_mobile

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class Configuration : AppCompatActivity() {
    private lateinit var txtPseudo: EditText
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)
        prefs = getSharedPreferences("MonFichierDeSauvegarde", MODE_PRIVATE)

        txtPseudo = findViewById(R.id.txtPseudo)

        val toolbar = findViewById<Toolbar>(R.id.tbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Habitudes"
        supportActionBar?.subtitle = "Paramètres"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        txtPseudo.setText(prefs.getString("pseudo", "bot"))

        findViewById<Button>(R.id.btnChangerPseudo).setOnClickListener {
            if (txtPseudo.text.isEmpty()) {
                Toast.makeText(this, "Il faut entrer un pseudo.", Toast.LENGTH_SHORT).show()
            } else {
                val editor = prefs.edit()
                editor.putString("pseudo", txtPseudo.text.toString())
                editor.apply()
            }
        }

        findViewById<Button>(R.id.btnMiseAZero).setOnClickListener {
            val editor = prefs.edit()
            for (i in 0..2)
                editor.putInt("Habitude$i", 0)
            editor.apply()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}