package com.example.tp1_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // auto connexion
        intent = Intent(this, Habitudes::class.java)
        intent.putExtra("accountName", "bot")
        startActivity(intent)

        // val btnLogin = findViewById<Button>(R.id.btnLogin)
        // btnLogin.setOnClickListener { connection() }

    }

    fun connection() {
        val name = findViewById<EditText>(R.id.txtPseudo).text.toString()
        val password = findViewById<EditText>(R.id.txtMdp).text.toString()
        Log.i("Tag", "connection tried with $name, $password.")

        if (name == "bot" && password == "123") {
            intent = Intent(this, Habitudes::class.java)
            intent.putExtra("accountName", name)
            startActivity(intent)
        }
        else
            Toast.makeText(this, "Pseudo ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
    }

}