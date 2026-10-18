
package com.example.nadini_3tib

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nadini_3tib.databinding.ActivityMainBinding
import com.example.nadini_3tib.pertemuan5.LimaActivity
import com.example.nadini_3tib.pertemuan6.SixthActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
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

        // Mengambil data SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        val user = sharedPref.getString("username", "Pengguna") ?: "Pengguna"

        // Menampilkan username
        binding.txtUsername.text = user

        // Password tidak ditampilkan untuk menjaga privasi
        binding.txtPassword.text = "••••••"

        // Tombol Snackbar
        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Item dihapus",
                Snackbar.LENGTH_LONG
            )
                .setAction("BATAL") {
                    Snackbar.make(
                        binding.root,
                        "Penghapusan dibatalkan",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                .show()
        }

        // Tombol AlertDialog
        binding.btnAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak bisa dikembalikan.")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Hapus") { dialog, _ ->
                    // Simulasi proses hapus
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }

        // Tombol kembali
        binding.btnKembali.setOnClickListener {
            finish()
        }

        // Navigasi ke Pertemuan 5
        binding.btnToLima.setOnClickListener {
            startActivity(
                Intent(this, LimaActivity::class.java)
            )
        }

        // Navigasi ke Pertemuan 6 (Fragment)
        binding.btnToSix.setOnClickListener {
            startActivity(
                Intent(this, SixthActivity::class.java)
            )
        }

        // Tombol Logout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->

                    // Menghapus data login
                    sharedPref.edit()
                        .clear()
                        .apply()

                    dialog.dismiss()

                    // Kembali ke halaman Login
                    val intent = Intent(
                        this,
                        LoginActivity::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}
