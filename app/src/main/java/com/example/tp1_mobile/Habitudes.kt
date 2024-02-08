package com.example.tp1_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class Habitudes : AppCompatActivity() {
    private lateinit var txtTitre: TextView
    private lateinit var txtCompteur: TextView
    private var indexHabitude = 0

    private val habitudes: Array<Habitude> = arrayOf(
        Habitude("Ne pas arriver en retard"),
        Habitude("Prendre une marche"),
        Habitude("Ne pas commettre de crimes de guerre")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitudes)
        txtTitre = findViewById(R.id.txtTitre)
        txtCompteur = findViewById(R.id.txtCompteur)

        val toolbar = findViewById<Toolbar>(R.id.tbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Habitudes"
        supportActionBar?.subtitle = intent.getStringExtra("accountName").toString()

        miseAJourHabitude()

        findViewById<Button>(R.id.btnAjouter).setOnClickListener {
            habitudes[indexHabitude].compteur++
            miseAJourHabitude()
        }

        findViewById<Button>(R.id.btnAvancer).setOnClickListener {
            indexHabitude = (indexHabitude + 1) % habitudes.size
            miseAJourHabitude()
        }

        findViewById<Button>(R.id.btnReculer).setOnClickListener {
            if (indexHabitude == 0)
                indexHabitude = habitudes.size - 1
            else
               indexHabitude--
            miseAJourHabitude()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.toolbar_menu, menu)
       return true
    }

    fun miseAJourHabitude() {
        val habit = habitudes[indexHabitude]
        txtTitre.text = habit.nom
        txtCompteur.text = habit.compteur.toString()
    }
}