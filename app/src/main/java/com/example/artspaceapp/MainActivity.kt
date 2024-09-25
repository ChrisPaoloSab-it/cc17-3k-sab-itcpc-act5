package com.example.artspaceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.splash_screen)

        val welcomeButton = findViewById<Button>(R.id.welcomeButton)
        welcomeButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}


    class MainActivity : AppCompatActivity() {

        private lateinit var artPiece: ImageView
        private lateinit var artTitle: TextView
        private lateinit var artist: TextView

        private data class Description(val imageResId: Int, val title: String, val artist: String)

        private val Gallery = listOf(
            Description(R.drawable.baler, "Baler on a Cloudy Day", "Pao (2017)"),
            Description(R.drawable.jobi, "Moonlit Mascot", "Pao (2019)"),
            Description(R.drawable.oreo, "Oreo", "Pao (2024)"),
            Description(R.drawable.ramshorn, "Slow Life", "Pao 2019"),
            Description(R.drawable.ulap, "Ulap", "Pao (2017)")
        )

        private var currentIndex = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            artPiece = findViewById(R.id.ArtPiece)
            artTitle = findViewById(R.id.ArtTitle)
            artist = findViewById(R.id.Artist)

            val previousButton = findViewById<Button>(R.id.PreviousButton)
            previousButton.setOnClickListener {
                currentIndex = (currentIndex - 1 + Gallery.size) % Gallery.size
                displayArt()
            }
            val nextButton = findViewById<Button>(R.id.NextButton)
            nextButton.setOnClickListener {
                currentIndex = (currentIndex + 1) % Gallery.size
                displayArt()
            }
            displayArt()

        }

        private fun displayArt() {
            val currentDescription = Gallery[currentIndex]
            artPiece.setImageResource(currentDescription.imageResId)
            artTitle.text = currentDescription.title
            artist.text = currentDescription.artist
        }
    }

