
package com.example.nadini_3tib

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_splash_screen)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // Mengambil data SharedPreferences
        val sharedPref = getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        )

        // Mengecek status login pengguna
        val isLogin = sharedPref.getBoolean("isLogin", false)

        // Jika sudah login, langsung ke MainActivity
        if (isLogin) {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
            return
        }

        // Jika belum login, tampilkan SplashScreen selama 2 detik
        lifecycleScope.launch {
            delay(2000)

            // Berpindah ke halaman Login
            val intent = Intent(
                this@SplashScreen,
                LoginActivity::class.java
            )

            startActivity(intent)
            finish()
        }
    }
}
