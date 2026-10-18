package com.example.nadini_3tib.pertemuan6

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.nadini_3tib.R
import com.example.nadini_3tib.databinding.ActivitySixthBinding

class SixthActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySixthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Menghubungkan Kotlin dengan activity_sixth.xml
        binding = ActivitySixthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur tampilan layar
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

        // Mengatur Toolbar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "MindCare"
            subtitle = "Kesehatan Mental dan Kesejahteraan"

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // Menampilkan Fragment pertama saat halaman dibuka
        if (savedInstanceState == null) {
            replaceFragment(
                SatuFragment(),
                addToBackStack = false
            )
        }

        // Tombol Fragment 1
        binding.btnFragment1.setOnClickListener {
            replaceFragment(SatuFragment())
        }

        // Tombol Fragment 2
        binding.btnFragment2.setOnClickListener {
            replaceFragment(DuaFragment())
        }

        // Tombol Fragment 3
        binding.btnFragment3.setOnClickListener {
            replaceFragment(TigaFragment())
        }
    }

    // Fungsi untuk mengganti Fragment
    private fun replaceFragment(
        fragment: Fragment,
        addToBackStack: Boolean = true
    ) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    // Mengatur tombol kembali pada Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
