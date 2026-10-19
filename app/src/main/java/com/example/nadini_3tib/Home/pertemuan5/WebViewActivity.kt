package com.example.nadini_3tib.Home.pertemuan5

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nadini_3tib.R
import com.example.nadini_3tib.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWebViewBinding.inflate(layoutInflater)
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

        // Pengaturan Toolbar MindCare
        binding.toolbar.title = "Politeknik Caltex Riau"
        binding.toolbar.subtitle = "MindCare WebView"

        // Menampilkan tombol panah kembali
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)

        // Fungsi tombol kembali
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Pengaturan WebView
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true

        // Membuka website PCR
        binding.webView.loadUrl("https://pcr.ac.id")
    }
}
