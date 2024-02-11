package com.example.tp1_mobile

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("MonFichierDeSauvegarde", MODE_PRIVATE)

        findViewById<Button>(R.id.btnLogin).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> {
                val name = findViewById<EditText>(R.id.txtPseudo).text.toString()
                val password = findViewById<EditText>(R.id.txtMdp).text.toString()
                Log.i("Tag", "connexion essayée avec $name, $password.")

                if (name == prefs.getString("pseudo", "bot") && password == "123") {
                    intent = Intent(this, Habitudes::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this, "Pseudo ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}