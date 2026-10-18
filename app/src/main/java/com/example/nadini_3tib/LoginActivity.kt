
package com.example.nadini_3tib

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nadini_3tib.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        // Mengambil SharedPreferences
        val sharedPref = getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        )

        // Tombol Login
        binding.btnLogin.setOnClickListener {

            // Mengambil username dan password
            val user = binding.edtUsername.text.toString().trim()
            val pass = binding.edtPassword.text.toString()

            // Pengecekan username dan password
            if (user.isNotEmpty() && user == pass) {

                // Menyimpan status login
                sharedPref.edit()
                    .putBoolean("isLogin", true)
                    .putString("username", user)
                    .apply()

                // Menampilkan pesan berhasil
                Toast.makeText(
                    this,
                    "Login berhasil! Selamat datang, $user",
                    Toast.LENGTH_SHORT
                ).show()

                // Berpindah ke MainActivity
                val intent = Intent(
                    this,
                    MainActivity::class.java
                )
                startActivity(intent)
                finish()

            } else {

                // Menampilkan dialog login gagal
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage(
                        "Username dan password tidak boleh kosong dan harus sama."
                    )
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}
