package com.example.tp1_mobile

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class Habitudes : AppCompatActivity(), View.OnClickListener {
    private lateinit var txtTitre: TextView
    private lateinit var txtCompteur: TextView

    private var indexHabitude = 0
    private lateinit var habitudes: Array<Habitude>

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitudes)

        txtTitre = findViewById(R.id.txtTitre)
        txtCompteur = findViewById(R.id.txtCompteur)

        prefs = getSharedPreferences("MonFichierDeSauvegarde", MODE_PRIVATE)
        indexHabitude = prefs.getInt("indexHabitude", 0)

        // toolbar
        val toolbar = findViewById<Toolbar>(R.id.tbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.titre)
        supportActionBar?.subtitle = prefs.getString("pseudo", "bot")

        habitudes = arrayOf(
            Habitude("Ne pas arriver en retard"),
            Habitude("Prendre une marche"),
            Habitude("Faire 50 push-ups")
        )
        for ((index, habitude) in habitudes.withIndex())
            habitude.compteur = prefs.getInt("Habitude$index", 0)

        // boutons
        findViewById<Button>(R.id.btnAjouter).setOnClickListener(this)
        findViewById<Button>(R.id.btnAvancer).setOnClickListener(this)
        findViewById<Button>(R.id.btnReculer).setOnClickListener(this)

        findViewById<ImageButton>(R.id.btnPartager).setOnClickListener {
            val message = "J’ai réussi " + habitudes[indexHabitude].compteur + " fois à faire «" + habitudes[indexHabitude].nom + "». "
            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }
            startActivity(intent)
        }

        miseAJourHabitude()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.toolbar_menu, menu)
       return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnParametres -> {
                intent = Intent(this, Configuration::class.java)
                startActivity(intent)
            }
        }

        return true
    }

    override fun onResume() {
        super.onResume()

        supportActionBar?.subtitle = prefs.getString("pseudo", "bot")
        for ((index, habitude) in habitudes.withIndex())
            habitude.compteur = prefs.getInt("Habitude$index", 0)
        miseAJourHabitude()
    }

    override fun onClick(v: View?) {
        val editor = prefs.edit()

        when (v?.id) {
            R.id.btnAjouter -> {
                habitudes[indexHabitude].compteur++
                editor.putInt("Habitude$indexHabitude", habitudes[indexHabitude].compteur)
            }

            R.id.btnAvancer -> {
                indexHabitude = (indexHabitude + 1) % habitudes.size
                editor.putInt("indexHabitude", indexHabitude)
            }

            R.id.btnReculer -> {
                if (indexHabitude == 0)
                    indexHabitude = habitudes.size - 1
                else
                    indexHabitude--
                editor.putInt("indexHabitude", indexHabitude)
            }
        }
        editor.apply()
        miseAJourHabitude()
    }

    private fun miseAJourHabitude() {
        val habit = habitudes[indexHabitude]
        txtTitre.text = habit.nom
        txtCompteur.text = habit.compteur.toString()
    }
}